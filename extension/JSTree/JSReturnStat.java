package JSTree;

public class JSReturnStat implements JSTree {

	private JSTree expr;
	
	public JSReturnStat(JSTree expr) {
		this.expr = expr;
	}
	
	@Override
	public String toCode() {
		return "return " + expr.toCode();
	}

}
