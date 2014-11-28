package assembly.tokens;

import tree.expr.IntLeaf;
import assembly.InstrToken;
import assembly.Register;

public class SubToken extends InstrToken {
	
	private Register dest;
	private Register op1;
	private Object op2;
	
	public SubToken(Register dest, Register op1, IntLeaf i) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = i;
		this.addRegister(dest, op1);
	}
	
	public SubToken(Register dest, Register op1, Object o) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = o;
	}
	
	@Override
	public String toString() {
		return "SUB " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}

}
