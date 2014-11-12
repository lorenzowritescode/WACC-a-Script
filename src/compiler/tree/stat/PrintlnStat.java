package tree.stat;

import tree.expr.ExprNode;

public class PrintlnStat extends StatNode {

	private ExprNode expr;
	
	public PrintlnStat(ExprNode expr) {
		this.expr = expr;
	}
}
