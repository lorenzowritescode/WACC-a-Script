package tree;

import symboltable.SymbolTable;
import tree.type.WACCType;

public abstract class ExprNode extends WACCTree {

	@Override
	public boolean check(SymbolTable st) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WACCType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
