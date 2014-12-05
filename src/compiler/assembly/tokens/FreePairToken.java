package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemFreeTokens;

public class FreePairToken extends InstrToken {
	
	public FreePairToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_PAIR);
	}
	
	@Override
	public String toString() {
		return "BL p_free_pair";
	}

}
