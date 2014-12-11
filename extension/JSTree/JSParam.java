package JSTree;

public class JSParam implements JSExpr {
	
	String ident;
	
	public JSParam(String ident) {
		this.ident = ident;
	}

	@Override
	public String toCode() {
		return ident;
	}

}
