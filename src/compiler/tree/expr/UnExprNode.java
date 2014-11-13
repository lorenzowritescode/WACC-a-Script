package tree.expr;

import org.antlr.v4.runtime.RuleContext;

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
	public boolean check(SymbolTable st, RuleContext ctx) {
		return operator.check(expr);
	}

	@Override
	public WACCType getType() {
		return operator.getType();
	}

}
