package tree.stat;

import tree.expr.ExprNode;

/**
 * Class to represent print statements
 * Rule: 'print' expr
 *
 */

public class PrintStat extends StatNode {
	
	private ExprNode expr;
	
	public PrintStat(ExprNode expr) {
		this.expr = expr;
	}
}
