package JSTree.expr;


public abstract class JSUnOpExpr implements JSExpr {
	
	private JSExpr expr;

	public JSUnOpExpr(JSExpr expr) {
		this.expr = expr;
	}

}
