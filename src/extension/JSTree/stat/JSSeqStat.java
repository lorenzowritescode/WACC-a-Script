package JSTree.stat;


public class JSSeqStat extends JSStat {
	
	private JSStat lhs;
	private JSStat rhs;
	
	public JSSeqStat(JSStat lhs, JSStat rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		return lhs.toCode() + ";" +
						"\n" + rhs.toCode();
	}

	@Override
	public int depthIncremented() {
		return lhs.depthIncremented() + rhs.depthIncremented();
	}

	@Override
	public boolean isAsync() {
		return lhs.isAsync() || rhs.isAsync();
	}

}
