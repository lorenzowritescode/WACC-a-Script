package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class DivideToken extends InstrToken {
	
	private Register dest;
	private Register op1;
	private Register op2;
	private String condition = "";
	
	public DivideToken(Register dest,  Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	public DivideToken(String condition, Register dest,  Register op1, Register op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "SDIV" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}
}
