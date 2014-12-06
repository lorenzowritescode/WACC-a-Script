package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class AddToken extends InstrToken{

	private Register dest;
	private Register op1;
	private Register op2;
	private String condition = "";
	private String shift = "";
	private boolean shiftSet = false;
	
	
	public AddToken(Register dest, Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	public AddToken(String condition, Register dest, Register op1, Register op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}
	
	public AddToken(Register dest, Register op1, Register op2, String shift) {
		this(dest, op1, op2);
		this.shift = shift;
		shiftSet = true;
	}
	
	@Override
	public String toString() {
		return shiftSet?
				"ADD" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString() + ", " + shift:
					"ADD" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}
	
}
