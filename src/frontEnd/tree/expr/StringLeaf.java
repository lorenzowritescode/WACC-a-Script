package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LoadStringToken;

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
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx) {
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
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitStringLeaf(this);
	}

}
