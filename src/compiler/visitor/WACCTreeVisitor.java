package visitor;

import tree.expr.*;

public abstract class WACCTreeVisitor<T> {
	public abstract T visitCharLeaf(CharLeaf node);
	public abstract T visitBoolLeaf(BoolLeaf node);
	public abstract T visitBinExprNode(BinExprNode node);
	public abstract T visitCharLeaf(CharLeaf node);
	public abstract T visitCharLeaf(CharLeaf node);
	public abstract T visitCharLeaf(CharLeaf node);
	
}
