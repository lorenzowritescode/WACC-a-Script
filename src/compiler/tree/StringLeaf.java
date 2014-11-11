package tree;

import symboltable.SymbolTable;

public class StringLeaf extends WACCTree {
	
	private String text;
	
	public StringLeaf(String text) {
		this.text = text;
	}

	@Override
	public boolean check(SymbolTable st) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.STRING;
	}

}
