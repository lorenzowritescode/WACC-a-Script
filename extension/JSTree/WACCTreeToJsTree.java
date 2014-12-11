package JSTree;

import tree.WACCTree;
import tree.expr.CharLeaf;
import visitor.WACCTreeVisitor;

public class WACCTreeToJsTree extends WACCTreeVisitor<JSTree> {

	private WACCTree progTree;

	public WACCTreeToJsTree(WACCTree progTree) {
		this.progTree = progTree;
	}
	
	@Override
	public JSTree visit(WACCTree node) {
		return node.accept(this);
	}
	
	@Override
	public JSTree visitCharLeaf(CharLeaf node) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
