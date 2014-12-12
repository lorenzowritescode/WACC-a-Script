package JSTree;

public class JSSeqStat implements JSTree {
	
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
