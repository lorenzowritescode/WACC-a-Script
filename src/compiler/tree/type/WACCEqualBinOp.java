package tree.type;

import tree.expr.ExprNode;

/* Contains specific behavior for Equals and Not Equals Binary Expressions
 * See WACCBinOp.java for more 
 * Rule: expr binary-oper expr
 * Where binary-oper is '!=' | '=='
 */

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
