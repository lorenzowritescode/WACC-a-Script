package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;
import assembly.system.SystemPrintTokens;

public class OverflowToken extends InstrToken {
	
	public OverflowToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemErrorTokens.OVERFLOW_ERROR.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(SystemErrorTokens.OVERFLOW_ERROR);
		errors.append(SystemErrorTokens.RUNTIME_ERROR);
		errors.append(SystemPrintTokens.PRINT_STRING);
		return errors;
	}
	
	@Override
	public String toString() {
		return "BLVS p_throw_overflow_error";
	}
	
}
