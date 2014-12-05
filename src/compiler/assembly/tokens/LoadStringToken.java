package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;

public class LoadStringToken extends InstrToken {
	
	private static int LABEL_COUNTER = 10;
	
	private Register dest;
	private String s;
	private String label;
	private String condition = "";
	
	public LoadStringToken(Register dest, String s) {
		this.dest = dest;
		this.s = s;
		this.addRegister(dest);
		label = "msg_" + LABEL_COUNTER++;
	}
	
	public LoadStringToken(String condition, Register dest, String s) {
		this(dest, s);
		this.condition = condition;
	}
	
	@Override
	public TokenSequence toPrepend() {
		TokenSequence msgString = new TokenSequence(new InstrToken() {
			@Override
			public String toString() {
				return label + ":"
						+ "\n\t.word " + s.length()
						+ "\n\t.ascii \""+ s + "\"";
			}
		});
		return msgString;
	}
	
	@Override
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", =" + label;
	}
}
