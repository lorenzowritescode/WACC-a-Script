package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import assembly.StackPosition;

public class StoreToken extends InstrToken {
	
	protected Register source;
	protected Register dest;
	protected int offset;
	protected String condition = "";
	protected boolean offsetSet = false;
	
	public StoreToken(Register source, Register destAddress) {
		this.source = source;
		this.dest = destAddress;
		this.addRegister(source, destAddress);
	}
	
	public StoreToken(Register source, Register destAddress, int offset) {
		this(source, destAddress);
		if (offset != 0) {
			this.offset = offset;
			offsetSet = true;
		}
	}
	
	public StoreToken(String condition, Register source, Register destAddress) {
		this(source, destAddress);
		this.condition = condition;
	}
	
	public StoreToken(String condition, Register source, Register destAddress, int offset) {
		this(source, destAddress);
		this.condition = condition;
		this.offset = offset;
		offsetSet = true;
	}

	@Override 
	public String toString() {
		return (offsetSet && offset != 0) ?
				"STR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + ", #" + offset + "]" :
					"STR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + "]";
	}
	
	
}
