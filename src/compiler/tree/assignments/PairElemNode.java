package tree.assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LoadAddressToken;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.PairType;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

/*
 * 
 */
public class PairElemNode extends Assignable implements AssignLhsNode {
	
	public enum ORDER {
		FST("fst", 4), SND("snd", 8);
		
		private String s;
		private int offset;
		
		ORDER(String s, int i) {
			this.s = s;
			this.offset = i;
		}
		
		private int getOffset() {
			return offset;
		}
		@Override
		public String toString() {
			return s;
		}
	}	
	
	private ExprNode expr;
	private ORDER order;

	
	//Expr here should be of type 'pairType'
	public PairElemNode(String fstOrSnd, ExprNode expr) {
		this.expr = expr;
		if (fstOrSnd.equals("fst")) {
			this.order = ORDER.FST;
		} else if (fstOrSnd.equals("snd")) {
			this.order = ORDER.SND;
		} else {
			throw new IllegalArgumentException("The `fstOrSnd` string must be either `fst` or `snd`.\n"
					+ "Given: " + fstOrSnd);
		}
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!(expr.getType() instanceof PairType)) {
			new InvalidTypeException("The `fst` and `snd` operators must take an expr of type PairType.\n"
					+ "Given: " + expr.getType().toString());
			return false;
		}
		return true;
	}
	
	@Override
	public WACCType getType() {
		PairType p = (PairType) expr.getType();
		if (this.order == ORDER.FST) {
			return p.getFstType();
		} else {
			return p.getSndType();
		}
	}

	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TokenSequence toAssembly(Register register) {
		return new TokenSequence(
				new LoadAddressToken(register, register, order.getOffset()),
				new LoadAddressToken(register, register)); 
	}
	
}
