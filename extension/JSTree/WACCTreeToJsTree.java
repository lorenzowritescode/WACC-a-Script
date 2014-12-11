package JSTree;

import tree.ProgNode;
import tree.WACCTree;
import tree.assignments.*;
import tree.expr.*;
import tree.func.*;
import tree.stat.*;
import visitor.WACCTreeVisitor;

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

	@Override
	public JSTree visitAssignStatNode(AssignStatNode node) {
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
	public JSTree visitExitStat(ExitStat node) {
		JSTree exitVal = visit(node.getExpr());
		return new JSExitStat(exitVal);
	}

	@Override
	public JSTree visitFreeStat(FreeStat node) {
		return EMPTY_NODE;
	}

	@Override
	public JSTree visitIfStatNode(IfStatNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSTree visitPrintLnStat(PrintLnStat node) {
		JSTree expr = visit(node.getExpr());
		return new JSPrintLn(expr);
	}

	@Override
	public JSTree visitPrintStat(PrintStat node) {
		JSTree expr = visit(node.getExpr());
		return new JSPrint(expr);
	}

	@Override
	public JSTree visitReadStatNode(ReadStatNode node) {
		JSTree lhs = visit(node.getLhs());
		return new JSReadStat(lhs);
	}

	@Override
	public JSTree visitReturnStatNode(ReturnStatNode node) {
		JSTree expr = visit(node.getExpr());
		return new JSReturnStat(expr);
	}

	@Override
	public JSTree visitSeqStatNode(SeqStatNode node) {
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
		return new JSSeqStat(lhs, rhs);
	}

	@Override
	public JSTree visitSkipStatNode(SkipStatNode node) {
		return EMPTY_NODE;
	}

	@Override
	public JSTree visitVarDecNode(VarDecNode node) {
		JSTree var = visit(node.getVar());
		JSTree rhs = visit(node.getRhsTree());
		return new JSVarDec(var, rhs);
	}

	@Override
	public JSTree visitWhileStatNode(WhileStatNode node) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
