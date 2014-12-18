package JSTree.expr;


public abstract class JSUnOpExpr extends JSExpr {
	
	protected JSExpr expr;

	public JSUnOpExpr(JSExpr expr) {
		this.expr = expr;
	}

}
