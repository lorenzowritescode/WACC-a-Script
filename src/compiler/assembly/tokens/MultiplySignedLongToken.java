package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class MultiplySignedLongToken extends InstrToken {

	private Register dest1;
	private Register dest2;
	private Register op1;
	private Register op2;
	private String condition = "";
	
	public MultiplySignedLongToken(Register dest1, Register dest2, Register op1, Register op2) {
		this.dest1 = dest1;
		this.dest2 = dest2;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest1, dest2, op1, op2);
	}
	
	public MultiplySignedLongToken(String condition, Register dest1, Register dest2, Register op1, Register op2) {
		this(dest1, dest2, op1, op2);
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "SMULL" + condition + " " + dest1.toString() + ", " + dest2.toString() 
						+ ", " + op1.toString() + ", " + op2.toString();
	}
	
}
