package assembly.tokens;

import tree.expr.IntLeaf;
import assembly.InstrToken;
import assembly.Register;

public class LoadToken extends InstrToken {
	
	private Register dest;
	private String source;
	private String condition;
	
	public LoadToken(Register dest, IntLeaf source) {
		this.condition = "";
		this.dest = dest;
		this.source = "=" + source.toString();
	}
	
	public LoadToken(Register dest, String label) {
		this.condition = "";
		this.dest = dest;
		this.source = "=" + label;
	}
	
	public LoadToken(Register dest, Register source) {
		this.condition = "";
		this.dest = dest;
		this.source = "[" + source.toString() + "]";
	}
	
	public LoadToken(String condition, Register dest, IntLeaf source) {
		this.condition = condition;
		this.dest = dest;
		this.source = "=" + source.toString();
	}
	
	public LoadToken(String condition, Register dest, String label) {
		this.condition = condition;
		this.dest = dest;
		this.source = "=" + label;
	}
	
	public LoadToken(String condition, Register dest, Register source) {
		this.condition = condition;
		this.dest = dest;
		this.source = "[" + source.toString() + "]";
	}

	@Override 
	public String toString() {
		return "LDR" + condition + " " + dest.toString() + ", " + source.toString();
	}
	
}
