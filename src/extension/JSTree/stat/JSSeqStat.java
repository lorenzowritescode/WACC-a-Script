package JSTree.stat;

import JSTree.JSTree;

public class JSSeqStat implements JSStat {
	
	private JSTree lhs;
	private JSTree rhs;
	
	public JSSeqStat(JSTree lhs, JSTree rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		return lhs.toCode() + ";" +
						"\n" + rhs.toCode();
	}

}
