package JSTree.stat;

import JSTree.expr.JSExpr;

public class JSPrint extends JSStat {

	private JSExpr expr;
	
	public JSPrint(JSExpr expr) {
		this.expr = expr;
	}

	@Override
	public String toCode() {
		return "core.print(" + expr.toCode() + ")";
	}
	
}
