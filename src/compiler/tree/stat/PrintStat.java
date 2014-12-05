package tree.stat;

import tree.assignments.ArrayElemNode;
import tree.expr.ExprNode;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintArrayElemToken;


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
		TokenSequence printSeq;
		if (expr instanceof ArrayElemNode) {
			printSeq = new TokenSequence(new PrintArrayElemToken(register));
		} else {
			printSeq = expr.getType().printAssembly(register);
		}
		return exprSeq.appendAll(printSeq);
	}
}
