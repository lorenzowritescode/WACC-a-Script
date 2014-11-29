package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class LoadToken extends InstrToken {
	
	private Register dest;
	private String source;
	private String condition = "";

//  Don't think we need this, leaving it just in case.	
//	public LoadToken(Register dest, IntLeaf source) {
//		this(dest);
//		this.source = source.toString();
//	}
	
//	public LoadToken(String condition, Register dest, IntLeaf source) {
//		this(condition, dest);
//		this.source = source.toString();
//	}
	
	public LoadToken(Register dest, String label) {
		this(dest);
		this.source = "=" + label;
	}
	
	public LoadToken(Register dest, Register source) {
		this(dest);
		this.source = "[" + source.toString() + "]";
		this.addRegister(source);
	}
	
	public LoadToken(Register dest, Register source, int offset) {
		this(dest);
		this.source = "[" + source.toString() + ", #" + offset + "]";
	}
	
	public LoadToken(String condition, Register dest, String label) {
		this(condition, dest);
		this.source = "=" + label;
	}
	
	public LoadToken(String condition, Register dest, Register source) {
		this(condition, dest);
		this.source = "[" + source.toString() + "]";
		this.addRegister(source);
	}
	
	public LoadToken(String condition, Register dest, Register source, int offset) {
		this(dest);
		this.source = "[" + source.toString() + ", #" + offset + "]";
		this.addRegister(source);
	}
	
	private LoadToken(Register dest) {
		this.dest = dest;
		this.addRegister(dest);
	}
	private LoadToken(String condition, Register dest) {
		this(dest);
		this.condition = condition;
		this.addRegister(dest);
	}

	@Override 
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", " + source;
	}
	
}
