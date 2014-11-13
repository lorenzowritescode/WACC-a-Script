package tree.stat;

import tree.expr.ExprNode;

public class PrintLnStat extends StatNode {

	private ExprNode expr;
	
	public PrintLnStat(ExprNode expr) {
		this.expr = expr;
	}
}
