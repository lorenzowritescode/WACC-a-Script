package assembly.tokens;

import assembly.InstrToken;

public class BranchLinkToken extends InstrToken {

	private String label;
	private String condition;
	
	public BranchLinkToken(String condition, String label) {
		this.condition = condition;
		this.label = label;
	}
	
	public BranchLinkToken(String label) {
		this.condition = "";
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "BL" + condition +  " " + label;
	}
}
