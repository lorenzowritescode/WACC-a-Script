package tree;

import symboltable.SymbolTable;


public class CharLeaf extends WACCTree {

	public CharLeaf(String text) {
		// TODO: what is contained in test??
	}

	@Override
	public boolean check( SymbolTable st ) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.CHAR;
	}

}
