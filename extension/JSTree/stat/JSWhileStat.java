package JSTree.stat;

import JSTree.expr.JSExpr;

public class JSWhileStat implements JSStat {
	
	private JSExpr condition;
	private JSStat loopBody;

	public JSWhileStat(JSExpr condition, JSStat loopBody) {
		this.condition = condition;
		this.loopBody = loopBody;
	}

	@Override
	public String toCode() {
		String output = "while (";
		output += condition.toCode();
		output += ") {\n";
		output += loopBody.toCode();
		output += "\n}\n";
		return output;
	}

}
