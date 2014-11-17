package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.type.WACCType;
import tree.type.WACCUnOp;

/* Represents a Unary Operator expression
 * Holds the operator and the expression
 * Checks expression type is compatible with the operator
 * Rule: unary-oper expr
 * Where unary-oper is '!' | '-' | 'len' | 'ord' | 'chr'
 */

public class UnExprNode extends ExprNode{

	private WACCUnOp operator;
	private ExprNode expr;

	public UnExprNode(WACCUnOp unaryOp, ExprNode expr) {
		this.operator = unaryOp;
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (!operator.check(expr)) {
			new InvalidTypeException("The type in the Unary operator expression is not valid", ctx);
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return operator.getType();
	}

}
