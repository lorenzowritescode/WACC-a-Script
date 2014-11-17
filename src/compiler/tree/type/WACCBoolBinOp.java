package tree.type;

import tree.expr.ExprNode;

public class WACCBoolBinOp extends WACCBinOp {
	
	/* Contains specific behavior for Boolean Binary Expressions
	 * See WACCBinOp.java for more 
	 */
	
	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == WACCType.BOOL && rhs.getType() ==  WACCType.BOOL;
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
