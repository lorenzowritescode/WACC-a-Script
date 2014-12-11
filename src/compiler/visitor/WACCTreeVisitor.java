package visitor;

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

public abstract class WACCTreeVisitor<T> {
	public abstract T visitCharLeaf(CharLeaf node);
	public abstract T visitBoolLeaf(BoolLeaf node);
	public abstract T visitBinExprNode(BinExprNode node);
	public abstract T visitIntLeaf(IntLeaf node);
	public abstract T visitPairLeaf(PairLeaf node);
	public abstract T visitPairLiterNode(PairLiterNode node);
	public abstract T visitStringLeaf(StringLeaf node);
	
	//Assignment
	public abstract T visitArgListNode(ArgListNode node);
	public abstract T visitArrayElemNode(ArrayElemNode node);
	public abstract T visitArrayLiterNode(ArrayLiterNode node);
	public abstract T visitCallStatNode(CallStatNode node);
	public abstract T visitNewPairNode(NewPairNode node);
	public abstract T visitPairElemNode(PairElemNode node);
}
