package tree.stat;

import tree.expr.ExprNode;
import visitor.WACCTreeVisitor;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintLnToken;

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
	
	@Override
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = expr.toAssembly(register);
		TokenSequence printSeq = expr.getType().printAssembly(register);
		InstrToken println = new PrintLnToken();
		exprSeq.appendAll(printSeq);
		exprSeq.append(println);
		return exprSeq;
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitPrintLnStat(this);
	}
}
