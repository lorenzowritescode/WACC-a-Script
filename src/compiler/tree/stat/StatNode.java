package tree.stat;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public abstract class StatNode extends WACCTree {

	@Override
	public boolean check( SymbolTable st, RuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}

}