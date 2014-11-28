package assembly.tokens;

import tree.expr.IntLeaf;
import assembly.InstrToken;
import assembly.Register;

public class AddToken extends InstrToken{

	private Register dest;
	private Register op1;
	private Object op2;
	private String condition = "";
	
	
	public AddToken(Register dest, Register op1, IntLeaf op2) {
		this(dest, op1);
		this.op2 = op2;
		this.addRegister(dest, op1);
	}
	
	public AddToken(Register dest, Register op1, Register op2) {
		this(dest, op1);
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	public AddToken(String condition, Register dest, Register op1, IntLeaf op2) {
		this(condition, dest, op1);
		this.op2 = op2;
		this.addRegister(dest, op1);
	}
	
	public AddToken(String condition, Register dest, Register op1, Register op2) {
		this(condition, dest, op1);
		this.op2 = op2;
		this.addRegister(dest, op1, op2);
	}
	
	private AddToken(String condition, Register dest, Register op1) {
		this.dest = dest;
		this.condition = condition;
	}
	
	private AddToken(Register dest, Register op1) {
		this.dest = dest;
		this.op1 = op1;
	}
	
	@Override
	public String toString() {
		return "ADD" + condition + " " + dest.toString() + ", " + op1.toString() + ", " + op2.toString();
	}
	
}
