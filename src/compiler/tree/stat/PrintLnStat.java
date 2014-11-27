package tree.stat;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
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

	@Override
	public TokenSequence toAssembly(Register register) {
		// TODO Auto-generated method stub
		return super.toAssembly(register);
	}
}
