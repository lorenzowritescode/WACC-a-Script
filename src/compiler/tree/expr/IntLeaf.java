package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public class IntLeaf extends ExprNode {

	private String value;
	
	public IntLeaf(String val) {
		this.value = val;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		// If the `value` has been tokenised as an int literal we can assume it is numeric.
		// Size is checked at runtime.
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.INT;
	}
	
}
