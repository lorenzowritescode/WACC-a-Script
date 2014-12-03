package assembly.tokens;

import assembly.InstrToken;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class FreePairToken extends InstrToken {
	
	public FreePairToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemTokens.FREE_PAIR);
	}
	
	@Override
	public String toString() {
		return "BL p_free_pair";
	}

}
