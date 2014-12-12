package JSTree;

public class JSReadStat implements JSTree {

	private JSTree lhs;
	
	public JSReadStat(JSTree lhs) {
		this.lhs = lhs;
	}
	
	@Override
	public String toCode() {
		return lhs.toCode() + " = read()";
	}

}
