package JSTree.expr;


public class JSVar implements JSExpr {
	
	private String ident;

	public JSVar(String ident) {
		this.ident = ident;
	}

	@Override
	public String toCode() {
		return ident;
	}

}
