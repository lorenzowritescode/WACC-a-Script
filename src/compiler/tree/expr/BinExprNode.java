package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCBinOp;
import tree.type.WACCType;

public class BinExprNode extends ExprNode {
	
	private ExprNode lhs;
	private WACCBinOp operator;
	private ExprNode rhs;

	public BinExprNode(ExprNode lhs, WACCBinOp operator, ExprNode rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return operator.check(lhs, rhs);
	}

	@Override
	public WACCType getType() {
		return operator.getType();
	}

}
