package JSTree.expr;

import JSTree.JSTree;

public class JSNewPair implements JSExpr {

	private JSTree fst;
	private JSTree snd;
	
	public JSNewPair(JSTree fst, JSTree snd) {
		this.fst = fst;
		this.snd = snd;
	}
	
	@Override
	public String toCode() {
		return "{fst: " + fst.toCode() + ", snd: " + snd.toCode() + "}"; 
	}

}
