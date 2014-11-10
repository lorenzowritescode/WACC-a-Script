package tree;

import symboltable.SymbolTable;

public class BoolLeaf extends WACCTree {
	private static final WACCType type = WACCType.BOOL;
	
	private boolean value;
	
	public BoolLeaf(String boolString) {
		switch (boolString) {
		case "true":
			this.value = true;
			break;
		case "false":
			this.value = false;
			break;
		default:
			throw new IllegalArgumentException("BoolLeaf can only be 'true' or 'false', string given " + boolString);
		}
	}

	@Override
	public boolean check(SymbolTable st) {
		return value;
	}

	@Override
	public WACCType getType() {
		return type;
	}

}
