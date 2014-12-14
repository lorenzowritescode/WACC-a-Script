package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovImmToken;

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
		try {
			int value = EscapedChar.match(text);
			return new TokenSequence(new MovImmToken(register, String.valueOf(value)));
		} catch (Exception e) {
			InstrToken tok = new MovImmToken(register, text);
			return new TokenSequence(tok);
		}
	}
	
	
	public enum EscapedChar {
		END_OF_STRING("\\0", '\0'),
		NEWLINE("\\n", '\n'),
		TAB("\\t", '\t'),
		CARRIAGE_RETURN("\\r", '\r'),
		FORM_FEED("\\f", '\f'),
		DOUBLE_QUOTES("\\\"", '"'),
		BACKSLASH("\\", '\\'),
		WHITESPACE(" ", ' '),
		APOSTROPHE("'", '\'');
		
		private String s;
		private char c;

		private EscapedChar(String s, char c) {
			this.s = s;
			this.c = c;
		}
		
		public int toInt() {
			return (int) c;
		}
		
		public static int match(String s) throws Exception {
			String unwrapped = unwrap(s);
			for (EscapedChar c:EscapedChar.values()) {
				if (c.getStr().equals(unwrapped))
					return c.toInt();
			}
			throw new Exception("The character was not found.");
		}

		private static String unwrap(String w) {
			return w.substring(1, w.length() - 1);
		}

		private String getStr() {
			return s;
		}
	}


	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitCharLeaf(this);
	}
	
	public String getText() {
		return text;
	}

}
