package JSTree;

import tree.ProgNode;
import tree.WACCTree;
import tree.assignments.*;
import tree.expr.*;
import tree.func.*;
import tree.stat.*;
import visitor.WACCTreeVisitor;
import JSTree.*;

public class WACCTreeToJsTree extends WACCTreeVisitor<JSTree> {

	private WACCTree progTree;

	public WACCTreeToJsTree(WACCTree progTree) {
		this.progTree = progTree;
	}
	
	public static final JSTree EMPTY_NODE = new JSTree() {
		@Override
		public String toCode() {
			return "";
		}
	};
	
	public String init() {
		JSTree finalTree = progTree.accept(this);
		return finalTree.toCode();
	}

	@Override
	public JSAssignStat visitAssignStatNode(AssignStatNode node) {
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
		return new JSAssignStat(lhs, rhs);
	}

	@Override
	public JSTree visitBlockStatNode(BlockStatNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSExitStat visitExitStat(ExitStat node) {
		JSTree exitVal = visit(node.getExpr());
		return new JSExitStat(exitVal);
	}

	@Override
	public JSTree visitFreeStat(FreeStat node) {
		return EMPTY_NODE;
	}

	@Override
	public JSPrintLn visitPrintLnStat(PrintLnStat node) {
		JSTree expr = visit(node.getExpr());
		return new JSPrintLn(expr);
	}

	@Override
	public JSPrint visitPrintStat(PrintStat node) {
		JSTree expr = visit(node.getExpr());
		return new JSPrint(expr);
	}

	@Override
	public JSReadStat visitReadStatNode(ReadStatNode node) {
		JSTree lhs = visit(node.getLhs());
		return new JSReadStat(lhs);
	}

	@Override
	public JSReturnStat visitReturnStatNode(ReturnStatNode node) {
		JSTree expr = visit(node.getExpr());
		return new JSReturnStat(expr);
	}

	@Override
	public JSSeqStat visitSeqStatNode(SeqStatNode node) {
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
		return new JSSeqStat(lhs, rhs);
	}

	@Override
	public JSTree visitSkipStatNode(SkipStatNode node) {
		return EMPTY_NODE;
	}

	@Override
	public JSVarDec visitVarDecNode(VarDecNode node) {
		JSTree var = visit(node.getVar());
		JSTree rhs = visit(node.getRhsTree());
		return new JSVarDec(var, rhs);
	}

	@Override
	public JSIfStat visitIfStatNode(IfStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getIfCond());
		JSStat thenStat = (JSStat) visit(node.getThenStat());
		JSStat elseStat = (JSStat) visit(node.getElseStat());
		return new JSIfStat(condition, thenStat, elseStat);
	}

	@Override
	public JSWhileStat visitWhileStatNode(WhileStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getLoopCond());
		JSStat loopBody = (JSStat) visit(node.getLoopBody());
		return new JSWhileStat(condition, loopBody);
	}
	

	
}
