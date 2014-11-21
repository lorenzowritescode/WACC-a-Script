package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class StoreToken extends InstrToken {
	
	private Register source;
	private String dest;
	private String condition;
	
	public StoreToken(String condition, Register source, Register destAddress) {
		this.condition = condition;
		this.source = source;
		this.dest = "[" + destAddress.toString() + "]";
	}
	
	public StoreToken(Register source, Register destAddress) {
		this.condition = "";
		this.source = source;
		this.dest = "[" + destAddress.toString() + "]";
	}


	@Override 
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", " + source.toString();
	}
	
	
}
