package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import tree.expr.IntLeaf;

public class MovToken extends InstrToken {
	
	private Register dest;
	private Object source;
	private String cond;

	public MovToken(String cond, Register r, IntLeaf n) {
		this.dest = r;
		this.source = n;
		this.cond = cond;
	}
	
	public MovToken(String cond, Register rDest, Register rSrc){
		this.dest = rDest;
		this.source = rSrc;
	}
	
	@Override
	public String toString() {
		return "MOV" + cond + " " + dest.toString() + ", " + source.toString();
	}
}
