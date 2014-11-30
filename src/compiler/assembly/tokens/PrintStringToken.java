package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.SystemTokens;
import assembly.TokenSequence;

public class PrintStringToken extends InstrToken {
	private static int LABEL_COUNTER = 5;
	
	private Register r;
	private String s;
	private String label;

	public PrintStringToken(Register r, String s) {
		this.r = r;
		this.s = s;
		label = "msg_" + LABEL_COUNTER++;
	}
	
	@Override
	public TokenSequence toPrepend() {
		
		TokenSequence msgString = SystemTokens.PRINT_STRING.toPrepend();
		msgString.append(new InstrToken() {
			@Override
			public String toString() {
				return label
						+ "\n\t.word " + s.length()
						+ "\n\t.ascii \""+ s + "\"";
			}
		});
		return msgString;
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
