package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintStringToken extends InstrToken {
	
	private Register r;

	public PrintStringToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemTokens.PRINT_STRING.toPrepend(); 
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemTokens.PRINT_STRING);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "BL p_print_string";
	}
	
}
