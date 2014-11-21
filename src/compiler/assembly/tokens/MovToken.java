package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;
import tree.expr.IntLeaf;

public class MovToken extends InstrToken {
	private Register dest;
	private Object source;
	private String condition;
	
	public MovToken(Register r, IntLeaf n) {
		this.dest = r;
		this.source = n;
		this.condition = "";
	}
	
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
