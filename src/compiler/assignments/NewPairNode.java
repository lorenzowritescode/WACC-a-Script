package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.PairType;
import tree.type.WACCType;

public class NewPairNode extends Assignable {
	
	private ExprNode fst;
	private ExprNode snd;
	
	public NewPairNode(ExprNode fst, ExprNode snd) {
		this.fst = fst;
		this.snd = snd;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		return new PairType(fst.getType(), snd.getType());
	}
}
