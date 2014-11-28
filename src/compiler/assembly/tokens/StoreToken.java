package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class StoreToken extends InstrToken {
	
	private Register source;
	private String dest;
	private String condition = "";
	
	public StoreToken(Register source, Register destAddress) {
		this.source = source;
		this.dest = "[" + destAddress.toString() + "]";
		this.addRegister(source, destAddress);
	}
	
	public StoreToken(Register source, Register destAddress, int offset) {
		this.source = source;
		this.dest = "[" + destAddress.toString() + ", #" + offset + "]";
		this.addRegister(source, destAddress);
	}
	
	public StoreToken(String condition, Register source, Register destAddress) {
		this.condition = condition;
		this.source = source;
		this.dest = "[" + destAddress.toString() + "]";
		this.addRegister(source, destAddress);
	}
	
	public StoreToken(String condition, Register source, Register destAddress, int offset) {
		this.condition = condition;
		this.source = source;
		this.dest = "[" + destAddress.toString() + ", #" + offset + "]";
		this.addRegister(source, destAddress);
	}


	@Override 
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", " + source.toString();
	}
	
	
}
