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
		return errors;
	}
	
	@Override
	public String toString() {
		return "BLVS p_throw_overflow_error";
	}
	
}
