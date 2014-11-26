package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

/* Represents the value of a String
 * Constructed with a String (e.g "hello")
 * Rule: '"' character* '"'
 */

public class StringLeaf extends ExprNode {
	
	private String text;
	
	public StringLeaf(String text) {
		this.text = text;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.STRING;
	}

	@Override
	public int weight() {
		return 1;
	}

}
