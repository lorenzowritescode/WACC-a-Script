package assembly.tokens;

import tree.expr.IntLeaf;
import assembly.InstrToken;
import assembly.Register;

public class SubToken extends InstrToken {
	
	private Register dest;
	private Register op1;
	private Object op2;
	private String condition = "";
	
	public SubToken(Register dest, Register op1, IntLeaf i) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = i;
		this.addRegister(dest, op1);
	}
	
	public SubToken(Register dest, Register op1, Register op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	public SubToken(String condition, Register dest, Register op1, IntLeaf i) {
		this(dest, op1, i);
		this.condition = condition;
	}
	
	public SubToken(String condition, Register dest, Register op1, Register op2) {
		this(dest, op1, op2);
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "\tSUB" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}

}
