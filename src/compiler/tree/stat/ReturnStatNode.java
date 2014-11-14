package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.FuncDecNode;
import tree.type.WACCType;

public class ReturnStatNode extends StatNode {
	private ExprNode expr;

	public ReturnStatNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		WACCTree node = this;
		while (!(node instanceof FuncDecNode)) {
			node = node.getParent();
		}
		FuncDecNode funcNode = (FuncDecNode) node;
		WACCType type = expr.getType();
		if (type != funcNode.returnType) {
			return false;
		}
		return true;
	}
}
