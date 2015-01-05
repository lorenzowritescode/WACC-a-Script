package JSTree.stat;

import JSTree.JSTree;

public class JSReturnStat extends JSStat {

	private JSTree expr;
	
	public JSReturnStat(JSTree expr) {
		this.expr = expr;
	}
	
	@Override
	public String toCode() {
		return "if (typeof callback !== 'undefined')\n\t"
					+ "{ callback(" + expr.toCode() + ") }\n"
				+ "else\n\t"
					+ "{ return " + expr.toCode() + "}";
	}

}
