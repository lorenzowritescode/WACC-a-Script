package tree;

import antlr.WACCParser.Variable_declarationContext;
import WACCExceptions.UndeclaredVariableException;
import symboltable.SymbolTable;

public class AssignStatNode extends StatNode {
	
	private AssignLhsNode lhs;
	private AssignRhsNode rhs;
	private Variable_declarationContext ctx;
	
	public AssignStatNode(Variable_declarationContext ctx, AssignLhsNode lhs, AssignRhsNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public boolean check(SymbolTable st) {
		if (st.containsRecursive(lhs.getIdent())) {	
			//TODO: complete check for assign stat node
			return true;
			
		} else {
			el.record(new UndeclaredVariableException(
					"Variable " + lhs.getIdent() + " has not been declared", ctx));
			return false;
		}
	}

}
