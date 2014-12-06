package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;
import assembly.system.SystemFormatterTokens;
import assembly.system.SystemFreeTokens;
import assembly.system.SystemPrintTokens;

public class FreePairToken extends InstrToken {
	
	public FreePairToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_PAIR, 
				SystemErrorTokens.RUNTIME_ERROR, 
				SystemPrintTokens.PRINT_STRING);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return new TokenSequence(
				SystemErrorTokens.NULL_REFERENCE_MESSAGE, 
				SystemFormatterTokens.STRING_FORMATTER);
	}
	
	@Override
	public String toString() {
		return "BL p_free_pair";
	}

}
