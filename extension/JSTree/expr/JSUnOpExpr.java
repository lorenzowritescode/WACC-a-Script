package JSTree.expr;


public abstract class JSUnOpExpr implements JSExpr {
	
	protected JSExpr expr;

	public JSUnOpExpr(JSExpr expr) {
		this.expr = expr;
	}

}
