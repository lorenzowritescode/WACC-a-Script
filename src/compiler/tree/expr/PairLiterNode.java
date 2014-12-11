package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LoadToken;

/**
 *	Rule: WACCType.NULL
 */
public class PairLiterNode extends ExprNode {

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return WACCType.NULL;
	}

	@Override
	public int weight() {
		//TODO: we think this is correct but might not be
		return 1;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		return new TokenSequence(
				new LoadToken(register, "0"));
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitPairLiterNode(this);
	}

}
