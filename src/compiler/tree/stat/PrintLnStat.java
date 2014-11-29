package tree.stat;

import tree.expr.ExprNode;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintLnToken;
import assembly.tokens.PrintStringToken;

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
		InstrToken print = new PrintStringToken(expr.toString());
		InstrToken println = new PrintLnToken();
		TokenSequence seq = new TokenSequence(print, println);
		return seq;
	}
}
