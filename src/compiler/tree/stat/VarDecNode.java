package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.InstrToken;
import assembly.Register;
import assembly.StackAllocator;
import assembly.StackPosition;
import assembly.TokenSequence;
import assembly.tokens.StoreToken;
import symboltable.SymbolTable;
import tree.assignments.Assignable;
import tree.expr.VarNode;
import tree.func.FuncDecNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.NotUniqueIdentifierException;

/**
 * Class to represent variable declaration statements
 * Rule: type ident '=' assign-rhs
 */

public class VarDecNode extends StatNode {
	private static StackAllocator sa = new StackAllocator();
	
	private Assignable rhsTree;
	private VarNode var;

	public VarDecNode(VarNode var, Assignable rhsTree) {
		this.var = var;
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		
		// First we check the identifier is unique and it is not a function
		if ( st.containsCurrent(var.getIdent()) && !(st.get(var.getIdent()) instanceof FuncDecNode) ) {
			new NotUniqueIdentifierException(
					"A variable with identifier " + var.getIdent() + " was already declared", ctx);
			return false;
		} 
		
		// We add the current var to the SymbolTable
		st.add(var.getIdent(), this);
		
		if ( !var.getType().isCompatible(rhsTree.getType()) ) {
			new IncompatibleTypesException(
					"The types of the rhs and lhs of the variable declaration do not match.\n"
							+ "LHS: " + var.getType().toString() + ",\n"
							+ "RHS: " + rhsTree.getType().toString(), ctx);
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return this.var.getType();
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		StackPosition pos = sa.allocate();
		var.setPos(pos);
		
		TokenSequence rhsSeq = rhsTree.toAssembly(register);
		InstrToken storeInVariable = new StoreToken(register, pos);
		
		return rhsSeq.append(storeInVariable);
	}
	
	
}
