package JSTree.stat;

import JSTree.JSTree;

public class JSAssignStat implements JSStat {

	private JSTree lhs;
	private JSTree rhs;

	public JSAssignStat(JSTree lhs, JSTree rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		return lhs.toCode() + " = " + rhs.toCode();
	}
	
	
}
