package tree.type;

import tree.expr.ExprNode;

public class WACCEqualBinOp extends WACCBinOp {
	
	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}
	
}
