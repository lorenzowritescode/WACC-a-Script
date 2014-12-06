package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;
import assembly.system.SystemFormatterTokens;
import assembly.system.SystemFreeTokens;
import assembly.system.SystemPrintTokens;

public class FreeArrayToken extends InstrToken {
	
	public FreeArrayToken() {
	}
	
	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(
				SystemFreeTokens.FREE_ARRAY, 
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
		return "BL p_free_array";
	}

}
