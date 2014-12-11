package JSTree;

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
