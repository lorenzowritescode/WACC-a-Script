package tree.stat;

import assembly.Register;
import assembly.TokenSequence;

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
	
	@Override 
	public TokenSequence toAssembly(Register r) {
		return statNode.toAssembly(r);
	}
}
