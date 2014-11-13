package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import assignments.AssignRhsNode;
import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.NotUniqueIdentifierException;

public class VarDecNode extends StatNode {
	
	private AssignRhsNode rhsTree;
	private WACCType varType;
	private String ident;

	public VarDecNode(WACCType varType, String ident, AssignRhsNode rhsTree) {
		this.ident = ident;
		this.varType = varType;
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		
		// First we check the identifier is unique
		if ( st.containsCurrent(ident) ) {
			el.record( new NotUniqueIdentifierException(
						"A variable with identifier " + ident + " was already declared", 
						ctx)
			);
			return false;
		} 
		
		// We add the current var to the SymbolTable
		st.add(ident, this);
		
		if ( varType != rhsTree.getType() ) {
			el.record( new IncompatibleTypesException("The types of the rhs and lhs of the variable declaration do not match." , ctx));
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return this.varType;
	}

}
