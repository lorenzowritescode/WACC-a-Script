package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class EorToken extends InstrToken {

	private Register dest;
	private Register op1;
	private Object op2;
	
	public EorToken(Register dest, Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	public EorToken(Register dest, Register op1, Object op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1);
	}
	
	@Override
	public String toString() {
		return "EOR " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}
	
}
