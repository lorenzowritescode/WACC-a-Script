package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintBoolToken extends InstrToken {
	
	private Register r;
	

	public PrintBoolToken(Register r) {
		this.r = r;
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.PRINT_BOOL.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemTokens.PRINT_BOOL);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "BL p_print_bool";
	}
	
}
