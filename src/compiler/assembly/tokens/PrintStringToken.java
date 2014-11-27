package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintStringToken extends InstrToken {
	private static int LABEL_COUNTER = 4;
	
	private String s;
	private String label;

	public PrintStringToken(String s) {
		this.s = s;
		label = "msg_" + LABEL_COUNTER++;
	}
	
	@Override
	public TokenSequence toPrepend() {
		return new TokenSequence(new InstrToken() {
			@Override
			public String toString() {
				return label
						+ "\n\t.word " + s.length()
						+ "\n\t.ascii \""+ s + "\"";
			}
		});
	}

	@Override
	public TokenSequence toAppend() {
		return new TokenSequence(SystemTokens.PRINT_STRING);
	}
	
	@Override
	public String toString() {
		return "LDR r0, =" + label + "\n"
				+ "BL p_print_string\n";
	}
	
}
