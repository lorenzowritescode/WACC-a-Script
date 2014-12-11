package JSTree;

import tree.WACCTree;
import tree.expr.CharLeaf;
import tree.stat.IfStatNode;
import tree.stat.WhileStatNode;
import visitor.WACCTreeVisitor;
import JSTree.*;

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

	@Override
	public JSTree visitIfStatNode(IfStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getIfCond());
		JSStat thenStat = (JSStat) visit(node.getThenStat());
		JSStat elseStat = (JSStat) visit(node.getElseStat());
		return new JSIfStat(condition, thenStat, elseStat);
	}

	@Override
	public JSTree visitWhileStatNode(WhileStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getLoopCond());
		JSStat loopBody = (JSStat) visit(node.getLoopBody());
		return new JSWhileStat(condition, loopBody);
	}
	

	
}
