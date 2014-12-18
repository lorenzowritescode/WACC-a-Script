package tree.stat;

import visitor.WACCTreeVisitor;
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

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitBlockStatNode(this);
	}
	
	public StatNode getStat() {
		return statNode;
	}
	
	@Override
	public boolean checkExpectation() {
		return statNode.checkExpectation();
	}
	
	@Override
	public int getVarCounter() {
		return statNode.getVarCounter();
	}
}
