package JSTree;

import JSTree.stat.JSStat;

public class JSChangeString extends JSStat {
	
	private String var;
	private int index;
	private String c;

	public JSChangeString(String var, int index, String c) {
		this.c = c;
		this.var = var;
		this.index = index;
	}

	@Override
	public String toCode() {
		return var + " = core.replaceString(" + var + ", " + index + ", " + c + ");";
	}

}
