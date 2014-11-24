package tree.stat;

import tree.expr.ExprNode;

/**
 * Class to represent println statements
 * Rule: 'println' expr
 *
 */

public class PrintLnStat extends StatNode {

	private ExprNode expr;
	
	public PrintLnStat(ExprNode expr) {
		this.expr = expr;
	}
}
