package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import assignments.ArrayElemNode;
import assignments.AssignLhsNode;
import assignments.Assignable;
import symboltable.SymbolTable;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndeclaredIdentifierException;

public class AssignStatNode extends StatNode {
	
	private AssignLhsNode lhs;
	private Assignable rhs;
	
	public AssignStatNode(AssignLhsNode lhs, Assignable rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		
		//check lhs is already declared
		if (!st.containsRecursive(lhs.getIdent())) {
			new UndeclaredIdentifierException( "Variable " + lhs.getIdent() + " has not been declared", ctx);
			return false;	
		}
		
		//Check types are compatible
		if (!lhs.getType().isCompatible(rhs.getType())) {
			new IncompatibleTypesException(
					"Cannot assign a " + rhs.getType().toString()
					+ " to a " + lhs.getType().toString(), ctx);
			return false;
		}
		
		//TODO: will this work with arrays? or will it assign the entire 
		// array to equal the rhs node?
		if (!(lhs instanceof ArrayElemNode)) {
			st.update(lhs.getIdent(), rhs);
		}
		return true;
		
	}

}
