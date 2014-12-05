package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.StorePreIndexToken;
import symboltable.SymbolTable;
import tree.type.WACCType;

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
	public TokenSequence printAssembly(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

}
