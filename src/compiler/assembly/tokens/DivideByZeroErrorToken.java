package assembly.tokens;

import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemErrorTokens;
import assembly.system.SystemPrintTokens;



public class DivideByZeroErrorToken extends InstrToken {
	
	public DivideByZeroErrorToken() {
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemErrorTokens.DIVIDE_BY_ZERO_ERROR.toPrepend();
	}
	
	@Override
	public TokenSequence toAppend() {
		TokenSequence errors = new TokenSequence(SystemErrorTokens.DIVIDE_BY_ZERO_ERROR);
		errors.append(SystemErrorTokens.RUNTIME_ERROR);
		errors.append(SystemPrintTokens.PRINT_STRING);
		return errors;
	}
	
	@Override
	public String toString() {
		return "BL p_check_divide_by_zero";
	}
}
