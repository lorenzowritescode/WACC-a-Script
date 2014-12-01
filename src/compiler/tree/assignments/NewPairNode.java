package tree.assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.MovRegToken;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.PairType;
import tree.type.WACCType;
import assembly.tokens.*;

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
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		
		//allocate memory on the heap for the pair elems (addresses)
		TokenSequence firstAlloc = mallocSequence(2);
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
		
		//allocate memory on the heap for the actual first pair elem and store it's address
		TokenSequence fstExp = fst.toAssembly(dest.getNext());
		TokenSequence secondAlloc = mallocSequence(1);
		TokenSequence storeFst = storeValAndAdd(dest);
		
		//allocate memory on the heap for the second pair elem
		TokenSequence sndExp = snd.toAssembly(dest.getNext());
		TokenSequence thirdAlloc = mallocSequence(1);
		TokenSequence storeSnd = storeValAndAdd(dest);
		
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
	private TokenSequence storeValAndAdd(Register dest) {
		StoreToken storeFst = new StoreToken(dest.getNext(), Register.R0);
		StoreToken storeAddFst = new StoreToken(Register.R0, dest);
		return new TokenSequence(storeFst, storeAddFst);
	}
}
