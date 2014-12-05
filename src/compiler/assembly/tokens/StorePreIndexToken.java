package assembly.tokens;

import assembly.Register;

public class StorePreIndexToken extends StoreToken {

	public StorePreIndexToken(Register destAddress, Register source) {
		super(destAddress, source);
	}
	public StorePreIndexToken(Register destAddress, Register source, int offset) {
		super(destAddress, source, offset);
	}
	
	public StorePreIndexToken(String condition, Register destAddress, Register source) {
		super(condition, destAddress, source);
	}
	
	public StorePreIndexToken(String condition, Register destAddress, Register source, int offset) {
		super(condition, destAddress, source, offset);
	}
	
	@Override 
	public String toString() {
		return (offsetSet && offset != 0) ?
				"STR" + condition +  " " + source.toString() + ", " + "[" + dest.toString() + ", #" + offset + "]!" :
					"STR" + condition +  " " + source.toString() + ", " + "[" + dest.toString() + "]!";
	}

}
