package tree.expr;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.WACCType;

public class IntLeaf extends WACCTree {

	private int value;
	
	public IntLeaf(int val) {
		this.value = val;
	}

	@Override
	public boolean check( SymbolTable st ) {
		return value < Integer.MAX_VALUE && value > Integer.MIN_VALUE;
	}

	@Override
	public WACCType getType() {
		return WACCType.INT;
	}
	
}
