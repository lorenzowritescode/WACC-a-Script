package tree.stat;

import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintStringToken;
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

	@Override
	public TokenSequence toAssembly(Register register) {
		return expr.toAssembly(register.getNext())
				.append(new PrintStringToken("hello"));
	}
}
