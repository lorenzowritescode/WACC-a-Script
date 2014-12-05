package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemFreeTokens;

public class FreeArrayToken extends InstrToken {
	
	public FreeArrayToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_ARRAY);
	}
	
	@Override
	public String toString() {
		return "BL p_free_array";
	}

}
