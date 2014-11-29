package assembly.tokens;

import assembly.InstrToken;

public class LabelToken extends InstrToken {
	
	private String label;
	
	public LabelToken(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label + ": ";
	}

}
