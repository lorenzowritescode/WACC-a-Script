package visitor;

import tree.expr.*;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;

public abstract class WACCTreeVisitor<T> {
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
}
