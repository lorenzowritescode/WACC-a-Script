package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class LoadAddressToken extends InstrToken {
	
	private Register dest;
	private Register source;
	private String condition = "";
	private boolean offsetSet;
	private int offset;

	public LoadAddressToken(Register dest, Register source) {
		this.dest = dest;
		this.source = source;
		offsetSet = false;
		this.addRegister(dest, source);
	}
	
	public LoadAddressToken(Register dest, Register source, int offset) {
		this(dest, source);
		this.offset = offset;
		offsetSet = true;
	}
	
	public LoadAddressToken(String condition, Register dest, Register source) {
		this(dest, source);
		this.condition = condition;
	}
	
	public LoadAddressToken(String condition, Register dest, Register source, int offset) {
		this(dest, source, offset);
		this.condition = condition;
	}
	
	
	@Override
	public String toString() {
		return offsetSet ?
				"\tLDR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + " #" + offset + "]" :
					"\tLDR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + "]";
	}
}
