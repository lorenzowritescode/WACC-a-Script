package assembly.tokens;

import assembly.InstrToken;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class DivideByZeroErrorToken extends InstrToken {
	
	public DivideByZeroErrorToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.DIVIDE_BY_ZERO_ERROR.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(SystemTokens.DIVIDE_BY_ZERO_ERROR);
		errors.append(SystemTokens.RUNTIME_ERROR);
		errors.append(SystemTokens.PRINT_STRING);
		return errors;
	}
	
	@Override
	public String toString() {
		return "\tBL p_check_divide_by_zero";
	}
}
