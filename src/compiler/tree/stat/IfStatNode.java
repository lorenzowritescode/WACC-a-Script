package tree.stat;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.ExprNode;
import tree.type.WACCType;

public class IfStatNode extends StatNode {
	
	private ExprNode expr;
	
	public IfStatNode(ExprNode expr) {
		this.expr = expr;
	}
	
	@Override
	public boolean check(SymbolTable st) {
		if (expr.getType() == WACCType.BOOL) {
			return true;
		} else {
			el.record(new InvalidTypeException("If statements should have an expr of type BOOL", ));
		    return false;
		}
	}
	

}
