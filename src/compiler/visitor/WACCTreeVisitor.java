package visitor;

import tree.*;
import tree.expr.*;
import tree.func.*;
import tree.assignments.*;
import tree.stat.*;

public abstract class WACCTreeVisitor<T> {
	public abstract T visitProgNoe(ProgNode node);
	
	// Expr
	public abstract T visitBinExprNode(BinExprNode node);
	public abstract T visitCharLeaf(CharLeaf node);
	public abstract T visitBoolLeaf(BoolLeaf node);
	public abstract T visitIntLeaf(IntLeaf node);
	public abstract T visitPairLeaf(PairLeaf node);
	public abstract T visitPairLiterNode(PairLiterNode node);
	public abstract T visitStringLeaf(StringLeaf node);
	public abstract T visitUnExprNode(UnExprNode node);
	public abstract T visitVarNode(VarNode node);
	
	// Func
	public abstract T visitFuncDecNode(FuncDecNode node);
	public abstract T visitParamListNode(ParamListNode node);
	public abstract T visitParamNode(ParamNode node);
	
	//Assignment
	public abstract T visitArgListNode(ArgListNode node);
	public abstract T visitArrayElemNode(ArrayElemNode node);
	public abstract T visitArrayLiterNode(ArrayLiterNode node);
	public abstract T visitCallStatNode(CallStatNode node);
	public abstract T visitNewPairNode(NewPairNode node);
	public abstract T visitPairElemNode(PairElemNode node);
	
	// Stat
	public abstract T visitAssignStatNode(AssignStatNode node);
	public abstract T visitBlockStatNode(BlockStatNode node);
	public abstract T visitExitStat(ExitStat node);
	public abstract T visitFreeStat(FreeStat node);
	public abstract T visitIfStatNode(IfStatNode node);
	public abstract T visitPrintLnStat(PrintLnStat node);
	public abstract T visitPrintStat(PrintStat node);
	public abstract T visitReadStatNode(ReadStatNode node);
	public abstract T visitReturnStatNode(ReturnStatNode node);
	public abstract T visitSeqStatNode(SeqStatNode node);
	public abstract T visitSkipStatNode(SkipStatNode node);
	public abstract T visitVarDecNode(VarDecNode node);
	public abstract T visitWhileStatNode(WhileStatNode node);
}
