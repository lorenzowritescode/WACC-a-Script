package tree.stat;

public class SeqStatNode extends StatNode {
	
	private StatNode lhs;
	private StatNode rhs;
	
	public SeqStatNode(StatNode lhs, StatNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
}
