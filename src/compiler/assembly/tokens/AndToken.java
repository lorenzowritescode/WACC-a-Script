package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class AndToken extends InstrToken {
	
	private Register dest;
	private Register op1;
	private Object op2;
	
	public AndToken(Register dest, Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	//TODO: Should this take bool leaf? or more general object? 
	//Spec says AND can take immediate value, but can it be anything else? 
	public AndToken(Register dest, Register op1, Object op2){
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1);
	}
	
	@Override
	public String toString() {
		return "AND " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}

}
