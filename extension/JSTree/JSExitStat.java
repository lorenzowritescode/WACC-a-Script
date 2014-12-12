package JSTree;

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
