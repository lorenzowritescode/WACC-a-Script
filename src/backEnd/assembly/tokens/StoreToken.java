package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class StoreToken extends InstrToken {
	
	protected Register source;
	protected Register dest;
	protected int offset;
	protected String condition = "";
	protected boolean offsetSet = false;
	
	public StoreToken(Register destAddress, Register source) {
		this.source = source;
		this.dest = destAddress;
		this.addRegister(source, destAddress);
	}
	
	public StoreToken(Register destAddress, Register source, int offset) {
		this(destAddress, source);
		if (offset != 0) {
			this.offset = offset;
			offsetSet = true;
		}
	}
	
	public StoreToken(String condition, Register destAddress, Register source) {
		this(destAddress, source);
		this.condition = condition;
	}
	
	public StoreToken(String condition, Register destAddress, Register source, int offset) {
		this(destAddress, source);
		this.condition = condition;
		this.offset = offset;
		offsetSet = true;
	}

	@Override 
	public String toString() {
		return (offsetSet && offset != 0) ?
				"STR" + condition +  " " + source.toString() + ", " + "[" + dest.toString() + ", #" + offset + "]" :
					"STR" + condition +  " " + source.toString() + ", " + "[" + dest.toString() + "]";
	}
	
	
}
