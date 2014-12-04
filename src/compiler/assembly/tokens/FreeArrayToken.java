package assembly.tokens;

import assembly.InstrToken;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class FreeArrayToken extends InstrToken {
	
	public FreeArrayToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemTokens.FREE_ARRAY);
	}
	
	@Override
	public String toString() {
		return "BL p_free_array";
	}

}
