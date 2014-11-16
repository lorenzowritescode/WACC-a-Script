package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.type.WACCType;
import tree.type.WACCUnOp;

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
