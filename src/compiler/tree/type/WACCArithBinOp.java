package tree.type;

import tree.expr.ExprNode;

public class WACCArithBinOp extends WACCBinOp {
	
	/* Contains specific behavior for Arithmetic Binary Expressions
	 * See WACCBinOp.java for more 
	 */
	
	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == rhs.getType() && lhs.getType() == WACCType.INT;
	}

	@Override
	public WACCType getType() {
		return WACCType.INT;
	}

}
