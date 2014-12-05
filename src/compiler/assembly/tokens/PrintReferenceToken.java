package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.system.SystemPrintTokens;

public class PrintReferenceToken extends InstrToken {

	private Register reg;
	
	public PrintReferenceToken(Register reg) {
		this.reg = reg;
	}
	
	@Override
	public TokenSequence toPrepend() {
		return SystemPrintTokens.PRINT_REF.toPrepend();
	}
	
	@Override 
	public TokenSequence toAppend() {
		return new TokenSequence(SystemPrintTokens.PRINT_REF);
	}
	
	@Override
	public String toString() {
		return "MOV r0, " + reg.toString() + "\n"
				+"\tBL p_print_reference";
	}
}
