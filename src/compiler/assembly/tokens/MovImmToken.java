package assembly.tokens;

import tree.expr.IntLeaf;
import assembly.Register;

public class MovImmToken extends MovToken {
	
	public MovImmToken(String condition, Register r, IntLeaf n) {
		this.dest = r;
		this.immSource = "=" + n.toString();
		this.condition = condition;
		this.addRegister(r);
	}
	
	/**
	 * General constructor for MOV tokens with immediate values
	 * @param r The destination register
	 * @param string The immediate source value (e.g. "=12")
	 */
	public MovImmToken(Register r, IntLeaf n) {
		this.dest = r;
		this.immSource = "=" + n.toString();
		this.addRegister(r);
	}
	
	@Override
	public String toString() {
		return "MOV" + condition + " " + dest.toString() + ", " + immSource;
	}
	
}
