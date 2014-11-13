package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public class BoolLeaf extends ExprNode {
	
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
	public boolean check(SymbolTable st, RuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
