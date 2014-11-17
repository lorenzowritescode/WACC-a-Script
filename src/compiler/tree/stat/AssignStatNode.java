package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import assignments.ArrayElemNode;
import assignments.AssignLhsNode;
import assignments.Assignable;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.func.FuncDecNode;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndeclaredIdentifierException;

public class AssignStatNode extends StatNode {
	
	private WACCTree lhs;
	private WACCTree rhs;
	
	public AssignStatNode(AssignLhsNode lhs, Assignable rhs) {
		this.lhs = (WACCTree) lhs;
		this.rhs = (WACCTree) rhs;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		
		//Check types are compatible
		if (!lhs.getType().isCompatible(rhs.getType())) {
			new IncompatibleTypesException(
					"Cannot assign a " + rhs.getType().toString()
					+ " to a " + lhs.getType().toString(), ctx);
			return false;
		}
		
		return true;
		
	}

}
