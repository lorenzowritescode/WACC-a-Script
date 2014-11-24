package tree.stat;

/**
 * Class to represent block statements
 * Rule: 'begin' stat 'end'
 *
 */

public class BlockStatNode extends StatNode {

	private StatNode statNode;

	public BlockStatNode(StatNode statNode) {
		this.statNode = statNode;
	}
	
}
