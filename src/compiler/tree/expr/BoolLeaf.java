package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovImmToken;

/* Represents the value of a Bool
 * Constructed with a String (e.g "true")
 * Rule: 'true' | 'false'
 */

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

	@Override
	public int weight() {
		return 1;
	}
	
	public boolean getValue() {
		return value;
	}

	@Override
	public TokenSequence toAssembly(Register r) {
		InstrToken tok;
		if(value) {
			tok = new MovImmToken(r, "#1");
		} else {
			tok = new MovImmToken(r, "#0");
		}
		return new TokenSequence(tok);
	}
	

}
