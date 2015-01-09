package JSTree.stat;

import JSTree.expr.JSExpr;

public class JSPrintLn extends JSStat {

	private JSExpr expr;
	
	public JSPrintLn(JSExpr expr) {
		this.expr = expr;
	}
	
	@Override
	public String toCode() {
		return "core.println(" + expr.toCode() + ")";
	}

}
