package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public class CharLeaf extends WACCTree {
	
	private String text;

	public CharLeaf(String text) {
		this.text = text;
		// TODO: what is contained in test??
	}

	@Override
	public boolean check( SymbolTable st, RuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.CHAR;
	}

}
