package JSTree.expr;

public class JSPair implements JSExpr {

	private String ident;
	
	public JSPair(String ident) {
		this.ident = ident;
	}
	
	@Override
	public String toCode() {
		return ident;
	}

}
