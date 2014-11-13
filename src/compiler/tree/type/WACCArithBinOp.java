package tree.type;

import tree.expr.ExprNode;

public class WACCArithBinOp extends WACCBinOp {
	
	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == rhs.getType() && lhs.getType() == WACCType.INT;
	}

	@Override
	public WACCType getType() {
		return WACCType.INT;
	}

}
