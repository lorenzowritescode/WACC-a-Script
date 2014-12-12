package JSTree.stat;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSExitStat implements JSTree {
	
	private JSExpr expr;

	public JSExitStat(JSExpr expr) {
		this.expr = expr;
	}

	@Override
	public String toCode() {
		return "return";
	}
	
	

}
