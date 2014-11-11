package tree;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public abstract class ExprNode extends WACCTree {

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WACCType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
