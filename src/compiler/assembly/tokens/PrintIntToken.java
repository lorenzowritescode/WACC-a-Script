package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintIntToken extends InstrToken {
	
	private Register r;
	
	public PrintIntToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.PRINT_INT.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemTokens.PRINT_INT);
	}
	
	
	
	public String toString() {
		return "\tMOV r0, " + r.toString() + "\n"
				+"\tBL p_print_int";
	}


}
