package tree.assignments;

import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.LoadToken;
import tree.WACCTree;

public abstract class Assignable extends WACCTree {
	
	//forms allocation token sequence (refactors)
	TokenSequence mallocSequence(int size) {
		LoadToken loadT = new LoadToken(Register.R0, Integer.toString(size*4));
		BranchLinkToken bl = new BranchLinkToken("malloc");
		return new TokenSequence(loadT, bl);
	}
}