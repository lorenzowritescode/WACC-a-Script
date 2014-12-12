package JSTree.stat;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSPrint implements JSTree {

	private JSExpr expr;
	
	public JSPrint(JSExpr expr) {
		this.expr = expr;
	}

	@Override
	public String toCode() {
		return "console.log(" + expr.toCode() + ")";
	}
	
}
