package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class AddImmToken extends InstrToken {
	private Register dest;
	private Register op1;
	private String op2;
	private String condition = "";
	
	
	public AddImmToken(Register dest, Register op1, String op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = "#" + op2;
		this.addRegister(dest, op1);
	}
	
	public AddImmToken(String condition, Register dest, Register op1, String op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "ADD" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2;
	}
}
