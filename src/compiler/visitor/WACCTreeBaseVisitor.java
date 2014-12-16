package visitor;

import tree.ProgNode;
import tree.WACCTree;
import tree.assignments.ArgListNode;
import tree.assignments.ArrayElemNode;
import tree.assignments.ArrayLiterNode;
import tree.assignments.CallStatNode;
import tree.assignments.NewPairNode;
import tree.assignments.PairElemNode;
import tree.expr.BinExprNode;
import tree.expr.BoolLeaf;
import tree.expr.CharLeaf;
import tree.expr.IntLeaf;
import tree.expr.PairLeaf;
import tree.expr.PairLiterNode;
import tree.expr.StringLeaf;
import tree.expr.UnExprNode;
import tree.expr.VarNode;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.stat.AssignStatNode;
import tree.stat.BlockStatNode;
import tree.stat.ExitStat;
import tree.stat.FreeStat;
import tree.stat.IfStatNode;
import tree.stat.PrintLnStat;
import tree.stat.PrintStat;
import tree.stat.ReadStatNode;
import tree.stat.ReturnStatNode;
import tree.stat.SeqStatNode;
import tree.stat.SkipStatNode;
import tree.stat.VarDecNode;
import tree.stat.WhileStatNode;

public class WACCTreeBaseVisitor<T> extends WACCTreeVisitor<T> {

	@Override
	public T visit(WACCTree node) {
		return null;
	}

	@Override
	public T visitProgNode(ProgNode node) {
		return null;
	}

	@Override
	public T visitBinExprNode(BinExprNode node) {
		return null;
	}

	@Override
	public T visitCharLeaf(CharLeaf node) {
		return null;
	}

	@Override
	public T visitBoolLeaf(BoolLeaf node) {
		return null;
	}

	@Override
	public T visitIntLeaf(IntLeaf node) {
		return null;
	}

	@Override
	public T visitPairLeaf(PairLeaf node) {
		return null;
	}

	@Override
	public T visitPairLiterNode(PairLiterNode node) {
		return null;
	}

	@Override
	public T visitStringLeaf(StringLeaf node) {
		return null;
	}

	@Override
	public T visitUnExprNode(UnExprNode node) {
		return null;
	}

	@Override
	public T visitVarNode(VarNode node) {
		return null;
	}

	@Override
	public T visitFuncDecNode(FuncDecNode node) {
		return null;
	}

	@Override
	public T visitParamListNode(ParamListNode node) {
		return null;
	}

	@Override
	public T visitParamNode(ParamNode node) {
		return null;
	}

	@Override
	public T visitArgListNode(ArgListNode node) {
		return null;
	}

	@Override
	public T visitArrayElemNode(ArrayElemNode node) {
		return null;
	}

	@Override
	public T visitArrayLiterNode(ArrayLiterNode node) {
		return null;
	}

	@Override
	public T visitCallStatNode(CallStatNode node) {
		return null;
	}

	@Override
	public T visitNewPairNode(NewPairNode node) {
		return null;
	}

	@Override
	public T visitPairElemNode(PairElemNode node) {
		return null;
	}

	@Override
	public T visitAssignStatNode(AssignStatNode node) {
		return null;
	}

	@Override
	public T visitBlockStatNode(BlockStatNode node) {
		return null;
	}

	@Override
	public T visitExitStat(ExitStat node) {
		return null;
	}

	@Override
	public T visitFreeStat(FreeStat node) {
		return null;
	}

	@Override
	public T visitIfStatNode(IfStatNode node) {
		return null;
	}

	@Override
	public T visitPrintLnStat(PrintLnStat node) {
		return null;
	}

	@Override
	public T visitPrintStat(PrintStat node) {
		return null;
	}

	@Override
	public T visitReadStatNode(ReadStatNode node) {
		return null;
	}

	@Override
	public T visitReturnStatNode(ReturnStatNode node) {
		return null;
	}

	@Override
	public T visitSeqStatNode(SeqStatNode node) {
		return null;
	}

	@Override
	public T visitSkipStatNode(SkipStatNode node) {
		return null;
	}

	@Override
	public T visitVarDecNode(VarDecNode node) {
		return null;
	}

	@Override
	public T visitWhileStatNode(WhileStatNode node) {
		return null;
	}

}
