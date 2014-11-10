package tree;

import symboltable.SymbolTable;

public abstract class StatNode extends WACCTree {

	@Override
	public boolean check( SymbolTable st ) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}

}
