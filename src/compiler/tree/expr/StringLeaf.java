package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public class StringLeaf extends ExprNode {
	
	private String text;
	
	public StringLeaf(String text) {
		this.text = text;
	}

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.STRING;
	}

}
