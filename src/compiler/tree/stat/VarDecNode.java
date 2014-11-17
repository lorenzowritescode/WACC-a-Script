package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import assignments.Assignable;
import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.NotUniqueIdentifierException;

/**
 * Class to represent variable declaration statements
 * Rule: type ident '=' assign-rhs
 */

public class VarDecNode extends StatNode {
	
	private Assignable rhsTree;
	private WACCType varType;
	private String ident;

	public VarDecNode(WACCType varType, String ident, Assignable rhsTree) {
		this.ident = ident;
		this.varType = varType;
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		
		// First we check the identifier is unique
		if ( st.containsCurrent(ident) ) {
			if (!(st.get(ident) instanceof FuncDecNode)) {
				new NotUniqueIdentifierException(
						"A variable with identifier " + ident + " was already declared", ctx);
				return false;
			}
		} 
		
		// We add the current var to the SymbolTable
		st.add(ident, this);
		
		if ( !varType.isCompatible(rhsTree.getType()) ) {
			new IncompatibleTypesException(
					"The types of the rhs and lhs of the variable declaration do not match.\n"
							+ "LHS: " + varType.toString() + ",\n"
							+ "RHS: " + rhsTree.getType().toString(), ctx);
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return this.varType;
	}

}
