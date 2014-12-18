package JSTree.stat;

import JSTree.WACCTreeToJsTree;
import JSTree.expr.JSExpr;

public class JSIfStat extends JSStat {
	
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
		output += "\n } ";
		if (elseStat != WACCTreeToJsTree.EMPTY_NODE) {
			output += "else {\n";
			output += elseStat.toCode();
			output += "\n}\n";
		}
		return output;
	}

}
