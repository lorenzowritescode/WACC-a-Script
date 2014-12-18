package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.system.SystemPrintTokens;

public class PrintBoolToken extends InstrToken {
	
	private Register r;
	

	public PrintBoolToken(Register r) {
		this.r = r;
		this.addRegister(r);
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_BOOL.toPrepend();
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_BOOL);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + r.toString() + "\n"
				+ "\t\tBL p_print_bool";
	}
	
}
