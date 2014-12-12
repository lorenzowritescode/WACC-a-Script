package JSTree.stat;

import JSTree.expr.JSExpr;

public class JSExitStat implements JSStat {
	
	private JSExpr expr;

	public JSExitStat(JSExpr expr) {
		this.expr = expr;
	}

	@Override
	public String toCode() {
		return "return";
	}
	
	

}
