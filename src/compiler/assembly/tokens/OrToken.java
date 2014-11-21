package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class OrToken extends InstrToken {
	
	private Register dest;
	private Register op1;
	private Object op2;
	
	public OrToken(Register dest, Register op1, Object op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	public String toString() {
		return "ORR " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}

}
