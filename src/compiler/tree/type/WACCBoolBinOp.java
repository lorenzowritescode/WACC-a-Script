package tree.type;

import tree.expr.ExprNode;

/* Contains specific behavior for Boolean Binary Expressions
 * See WACCBinOp.java for more
 * Rule: expr binary-oper expr
 * Where binary-oper is '&&' or '||' 
 */

public abstract class WACCBoolBinOp extends WACCBinOp {

	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == WACCType.BOOL && rhs.getType() ==  WACCType.BOOL;
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
