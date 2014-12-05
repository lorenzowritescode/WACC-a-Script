package assembly.tokens;

import assembly.Register;

public class StorePreIndexToken extends StoreToken {

	public StorePreIndexToken(Register source, Register destAddress) {
		super(source, destAddress);
	}
	public StorePreIndexToken(Register source, Register destAddress, int offset) {
		super(source, destAddress, offset);
	}
	
	public StorePreIndexToken(String condition, Register source, Register destAddress) {
		super(condition, source, destAddress);
	}
	
	public StorePreIndexToken(String condition, Register source, Register destAddress, int offset) {
		super(condition, source, destAddress, offset);
	}
	
	@Override 
	public String toString() {
		return (offsetSet && offset != 0) ?
				"STR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + ", #" + offset + "]!" :
					"STR" + condition +  " " + dest.toString() + ", " + "[" + source.toString() + "]!";
	}

}
