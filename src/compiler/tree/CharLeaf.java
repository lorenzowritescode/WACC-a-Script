package tree;

import symboltable.SymbolTable;


public class CharLeaf extends WACCTree {
	
	private String text;

	public CharLeaf(String text) {
		this.text = text;
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
