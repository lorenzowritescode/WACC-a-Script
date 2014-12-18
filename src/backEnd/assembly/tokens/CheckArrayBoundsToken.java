package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;
import assembly.system.SystemPrintTokens;

public class CheckArrayBoundsToken extends InstrToken {

	public CheckArrayBoundsToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemErrorTokens.CHECK_BOUNDS.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(
				SystemErrorTokens.CHECK_BOUNDS,
				SystemErrorTokens.RUNTIME_ERROR,
				SystemPrintTokens.PRINT_STRING);
		return errors;
	}
	
	@Override
	public String toString() {
		return "BL p_check_array_bounds";
	}
}
