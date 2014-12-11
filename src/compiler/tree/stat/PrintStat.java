package tree.stat;

import tree.expr.ExprNode;
import visitor.WACCTreeVisitor;
import assembly.Register;
import assembly.TokenSequence;


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

	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		TokenSequence printSeq = expr.getType().printAssembly(register);
		return exprSeq.appendAll(printSeq);
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitPrintStat(this);
	}
}
