package assembly.tokens;

import assembly.InstrToken;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class OverflowToken extends InstrToken {
	
	public OverflowToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.OVERFLOW_ERROR.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(SystemTokens.OVERFLOW_ERROR);
		errors.append(SystemTokens.RUNTIME_ERROR);
		errors.append(SystemTokens.PRINT_STRING);
		return errors;
	}
	
	@Override
	public String toString() {
		return "\tBLVS p_throw_overflow_error";
	}
	
}
