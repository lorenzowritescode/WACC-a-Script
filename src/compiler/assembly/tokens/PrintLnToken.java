package assembly.tokens;

import assembly.InstrToken;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintLnToken extends InstrToken {

	public PrintLnToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.PRINT_LN.toPrepend();
	}
	
	@Override 
	public TokenSequence toAppend() {
		return new TokenSequence(SystemTokens.PRINT_STRING);
	}
	
	@Override
	public String toString() {
		return "BL p_print_ln";
	}
	
}
