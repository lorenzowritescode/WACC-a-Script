package tree.expr;

import symboltable.SymbolTable;
import tree.type.WACCType;

public class CharLeaf extends ExprNode {
	
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
