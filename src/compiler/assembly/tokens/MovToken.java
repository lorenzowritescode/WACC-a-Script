package assembly.tokens;

import assembly.Register;
import tree.expr.IntLeaf;

public class MovToken {
	private Register dest;
	private Object source;

	public MovToken(Register r, IntLeaf n) {
		this.dest = r;
		this.source = n;
	}
	
	public MovToken(Register rDest, Register rSrc){
		this.dest = rDest;
		this.source = rSrc;
	}
	
	@Override
	public String toString() {
		return "MOV " + dest.toString() + ", " + source.toString();
	}
}
