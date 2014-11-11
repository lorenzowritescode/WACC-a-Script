package tree.expr;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public abstract class ExprNode extends WACCTree {

	//Abstract class for all expressions to extend
	
	@Override
	public boolean check(SymbolTable st) {
		return false;
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
