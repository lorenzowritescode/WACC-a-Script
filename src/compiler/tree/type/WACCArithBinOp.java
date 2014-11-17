package tree.type;

import tree.expr.ExprNode;

/* Contains specific behavior for Arithmetic Binary Expressions
 * See WACCBinOp.java for more 
 * Rule: expr binary-oper expr
 * Where binary-oper is '+' | '-' | '*' | '/' | '%'
 */

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
