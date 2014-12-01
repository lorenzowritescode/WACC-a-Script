package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LoadStringToken;
import assembly.tokens.LoadToken;
import assembly.tokens.PrintStringToken;

/* Represents the value of a String
 * Constructed with a String (e.g "hello")
 * Rule: '"' character* '"'
 */

public class StringLeaf extends ExprNode {
	private static int LABEL_COUNTER = 5;
	
	private String text;
	private String label;
	
	public StringLeaf(String text) {
		this.text = text;
		label = "msg_" + LABEL_COUNTER++;
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
	
	@Override
	public String toString() {
		return text;
	}
	
	public TokenSequence toAssembly(Register r) {
		return new TokenSequence(new LoadStringToken(r, text));
	}

	@Override
	public TokenSequence printAssembly(Register register) {
		InstrToken print = new PrintStringToken(register);
		return new TokenSequence(print);
	}

}
