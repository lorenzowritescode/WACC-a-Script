package tree.assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.PairType;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovRegToken;
import assembly.tokens.StoreToken;

public class NewPairNode extends Assignable {
	
	private ExprNode fst;
	private ExprNode snd;
	private final int PAIR_SIZE = 4;
	
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
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		
		//allocate memory on the heap for the pair elems (addresses)
		TokenSequence firstAlloc = mallocSequence(2, PAIR_SIZE);
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
		
		//allocate memory on the heap for the actual first pair elem and store it's address
		TokenSequence fstExp = fst.toAssembly(dest.getNext());
		TokenSequence secondAlloc = mallocSequence(1, fst.getType().getVarSize());
		TokenSequence storeFst = storeValAndAdd(fst, dest, 0);
		
		//allocate memory on the heap for the second pair elem
		TokenSequence sndExp = snd.toAssembly(dest.getNext());
		TokenSequence thirdAlloc = mallocSequence(1, snd.getType().getVarSize());
		TokenSequence storeSnd = storeValAndAdd(snd, dest, 4);
		
		//append all the TokenSequences
		firstAlloc
			.append(movReg1)
			.appendAll(fstExp)
			.appendAll(secondAlloc)
			.appendAll(storeFst)
			.appendAll(sndExp)
			.appendAll(thirdAlloc)
			.appendAll(storeSnd);
		
		return firstAlloc;
	}

	//forms a TokenSequence for storing the values on the heap and storing address in pair
	private TokenSequence storeValAndAdd(ExprNode expr, Register dest, int offset) {
		StoreToken store = expr.getType().storeAssembly(Register.R0, dest.getNext());
		//StoreToken store = new StoreToken(Register.R0, dest.getNext());
		StoreToken storeAdd = new StoreToken(dest, Register.R0,  offset);
		return new TokenSequence(store, storeAdd);
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitNewPairNode(this);
	}
	
	public ExprNode getSnd() {
		return snd;
	}
	
	public ExprNode getFst() {
		return fst;
	}
}
