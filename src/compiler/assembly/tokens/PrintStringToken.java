package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.system.SystemPrintTokens;

public class PrintStringToken extends InstrToken {
	
	private Register r;

	public PrintStringToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_STRING.toPrepend(); 
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_STRING);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\tBL p_print_string";
	}
	
}
