package tree.stat;

import tree.expr.ExprNode;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintStringToken;

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
		InstrToken print = new PrintStringToken(expr.toString());
		TokenSequence seq = new TokenSequence(print);
		return seq;
	}
}
