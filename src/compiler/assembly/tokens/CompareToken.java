package assembly.tokens;

import assembly.ImmValue;
import assembly.InstrToken;
import assembly.Register;

public class CompareToken extends InstrToken{
	
	private Register op1;
	private Register op2;
	private ImmValue i;
	//the shift string is used to shift one of the registers
	//Is required for some multiplication calculations
	private String shift = "";
	
	public CompareToken(Register op1, Register op2) {
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(op1, op2);
	}
	
	public CompareToken(Register op1, Register op2, String shift) {
		this.op1 = op1;
		this.op2 = op2;
		this.shift = shift;
		this.addRegister(op1, op2);
	}
	
	public CompareToken(Register op1, ImmValue i) {
		this.op1 = op1;
		this.i = i;
	}
	
	@Override
	public String toString() {
		if (op2 != null) {
			return "CMP " + op1.toString() + ", " + op2.toString() + ", " + shift;
		} else {
			return "CMP " + op1.toString() + ", " + i.toString();
		}
	}
}
