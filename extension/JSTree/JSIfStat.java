package JSTree;

public class JSIfStat implements JSStat {
	
	private JSExpr cond;
	private JSStat thenStat;
	private JSStat elseStat;

	public JSIfStat(JSExpr cond, JSStat thenStat, JSStat elseStat) {
		this.cond = cond;
		this.thenStat = thenStat;
		this.elseStat = elseStat;
	}

	@Override
	public String toCode() {
		String output = "if (";
		output += cond.toCode() + ") {\n";
		output += thenStat.toCode();
		output += "\n } else {\n";
		output += elseStat.toCode();
		output += "\n}\n";
		return output;
	}

}
