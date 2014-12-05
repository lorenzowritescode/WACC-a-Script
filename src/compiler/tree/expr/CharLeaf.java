package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovImmToken;
import assembly.tokens.PrintCharToken;

/* Represents the value of a Character
 * Constructed with a String (e.g "A")
 * Rule: any-ASCII-character-except-'\'-'''-'"' | '\' escaped-char
 */

public class CharLeaf extends ExprNode {
	
	private String text;

	public CharLeaf(String text) {
		this.text = text;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.CHAR;
	}

	@Override
	public int weight() {
		return 1;
	}
	
	@Override
	public TokenSequence toAssembly(Register register) {
		InstrToken tok = new MovImmToken(register, "#"+text);
		return new TokenSequence(tok);
	}

}
