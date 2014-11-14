package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.PairTypeNode;
import tree.type.WACCType;

public class PairLeaf extends ExprNode {

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
		return new PairTypeNode(fstType, sndType);
	}

	public String getIdent() {
		return ident;
	}
	
}
