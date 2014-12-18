package JSTree.func;

import JSTree.expr.JSExpr;

public class JSParam extends JSExpr {
	
	String ident;
	
	public JSParam(String ident) {
		this.ident = ident;
	}

	@Override
	public String toCode() {
		return ident;
	}

}
