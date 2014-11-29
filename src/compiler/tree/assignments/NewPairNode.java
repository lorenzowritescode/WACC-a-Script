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
		
		TokenSequence firstAlloc = mallocSequence(8);
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
		
		TokenSequence fstExp = fst.toAssembly(dest.getNext());
		TokenSequence secondAlloc = mallocSequence(4);
		StoreToken storeFst = new StoreToken(dest.getNext(), Register.R0);
		StoreToken storeAddFst = new StoreToken(Register.R0, dest);
		
		TokenSequence sndExp = snd.toAssembly(dest.getNext());
		TokenSequence thirdAlloc = mallocSequence(4);
		StoreToken storeSnd = new StoreToken(dest.getNext(), Register.R0);
		StoreToken storeAddSnd = new StoreToken(Register.R0, dest, 4);
		
		
		return null;
	}
	
	//forms allocation token sequence (refactors)
	private TokenSequence mallocSequence(int size) {
		LoadToken loadT = new LoadToken(Register.R0, Integer.toString(size));
		BranchLinkToken bl = new BranchLinkToken("malloc");
		return new TokenSequence(loadT, bl);
	}
}
