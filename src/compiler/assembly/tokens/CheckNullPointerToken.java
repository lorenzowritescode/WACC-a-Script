package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;

public class CheckNullPointerToken extends InstrToken {

	public CheckNullPointerToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemErrorTokens.NULL_POINTER_CHECK.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(
				SystemErrorTokens.NULL_POINTER_CHECK,
				SystemErrorTokens.RUNTIME_ERROR);
		return errors;
	}
	
	@Override
	public String toString() {
		return "BL p_check_null_pointer";
	}
}
