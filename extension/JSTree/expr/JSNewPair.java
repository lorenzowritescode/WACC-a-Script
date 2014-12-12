package JSTree.expr;

import JSTree.JSTree;

public class JSPair implements JSTree {

	private JSTree fst;
	private JSTree snd;
	
	public JSPair(JSTree fst, JSTree snd) {
		this.fst = fst;
		this.snd = snd;
	}
	
	@Override
	public String toCode() {
		return "{fst: " + fst.toCode() + ", snd: " + snd.toCode() + "}"; 
	}

}
