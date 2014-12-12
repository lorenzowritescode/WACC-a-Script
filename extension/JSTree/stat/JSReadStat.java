package JSTree.stat;

import JSTree.JSTree;

public class JSReadStat implements JSStat {

	private JSTree lhs;
	
	public JSReadStat(JSTree lhs) {
		this.lhs = lhs;
	}
	
	@Override
	public String toCode() {
		return lhs.toCode() + " = read()";
	}

}
