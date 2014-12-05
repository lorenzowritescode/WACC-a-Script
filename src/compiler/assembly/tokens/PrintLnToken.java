package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemPrintTokens;

public class PrintLnToken extends InstrToken {

	public PrintLnToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_LN.toPrepend();
	}
	
	@Override 
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_LN);
	}
	
	@Override
	public String toString() {
		return "BL p_print_ln";
	}
	
}
