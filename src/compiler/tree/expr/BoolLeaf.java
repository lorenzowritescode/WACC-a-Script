package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public class BoolLeaf extends ExprNode {
	
	private boolean value;
	private String stringValue;
	
	public BoolLeaf(String boolString) {
		this.stringValue = boolString;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {		
		switch (this.stringValue) {
			case "true":
				this.value = true;
				return true;
			case "false":
				this.value = false;
				return true;
			default:
				new IllegalArgumentException("BoolLeaf can only be 'true' or 'false', string given " + stringValue);
				return false;
		}
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
