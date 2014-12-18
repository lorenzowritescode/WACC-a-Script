package JSTree.expr;

public class JSPair extends JSExpr {

	private String ident;
	
	public JSPair(String ident) {
		this.ident = ident;
	}
	
	@Override
	public String toCode() {
		return ident;
	}

}
