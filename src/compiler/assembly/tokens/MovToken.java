package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import tree.expr.IntLeaf;


public class MovToken extends InstrToken {
	private Register dest;
	private Object source;
	private String condition;
	
	/**
	 * General constructor for MOV tokens with immediate values
	 * @param r The destination register
	 * @param string The immediate source value (e.g. "=12")
	 */
	public MovToken(Register r, String string) {
		this.dest = r;
		this.source = string;
		this.condition = "";
	}
	
	/**
	 * Constructor for register to register copy.
	 * @param rDest The destination register
	 * @param rSrc  The source register
	 */
	public MovToken(Register rDest, Register rSrc){
		this.dest = rDest;
		this.source = rSrc;
		this.condition = "";
	}

	public MovToken(String condition, Register r, IntLeaf n) {
		this.dest = r;
		this.source = n;
		this.condition = condition;
	}
	
	public MovToken(String condition, Register rDest, Register rSrc){
		this.dest = rDest;
		this.source = rSrc;
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		return "MOV" + condition + " " + dest.toString() + ", " + source.toString();
	}
}
