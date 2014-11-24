package tree.type;

import tree.expr.ExprNode;

/* Contains specific behavior for Comparative Binary Expressions
 * See WACCBinOp.java for more 
 * Rule: expr binary-oper expr
 * Where binary-oper is '>' | '>=' | '<' | '<='
 */

public class WACCCompBinOp extends WACCBinOp {
	
	@Override
	public boolean check(ExprNode lhs, ExprNode rhs) {
		if (lhs.getType() == WACCType.INT)
			return rhs.getType() == WACCType.INT;
		else if (lhs.getType() == WACCType.CHAR)
			return rhs.getType() == WACCType.CHAR;
		else
			return false;
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
