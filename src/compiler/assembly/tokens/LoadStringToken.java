package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;

public class LoadStringToken extends InstrToken {
	
	private static int LABEL_COUNTER = 12;
	
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
						+ "\n\t\t.word " + countActualLength(s)
						+ "\n\t\t.ascii "+ s;	
			}
			
			@Override
			public boolean requiresTab() {
				return false;
			}
		});
		return msgString;
	}
	
	private static int countActualLength(String s) {
		String s2 = s.replaceAll("\\\\", ""); // Escaped chars must be counted as one
		s2 = s2.substring(1, s2.length() - 1); // We remove the double quotes from the count
		return s2.length();
	}

	@Override
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", =" + label;
	}
}
