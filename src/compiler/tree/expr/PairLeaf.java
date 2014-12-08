package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.PairType;
import tree.type.WACCType;
import assembly.Register;
import assembly.TokenSequence;

/** Represents a declared pair
 * Contains type information for 1st and 2nd elements of pair
 * Contains identifier string
 */

public class PairLeaf extends ExprNode {
	
	private final int VAR_SIZE = 4;
	private WACCType fstType;
	private WACCType sndType;
	private String ident;
	
	public PairLeaf(String ident, WACCType fstType, WACCType sndType) {
		this.fstType = fstType;
		this.sndType = sndType;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return new PairType(fstType, sndType);
	}

	public String getIdent() {
		return ident;
	}

	@Override
	public int weight() {
		return 1;
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		return null;
	}
	
	
}
