package JSTree;

public class JSVar implements JSTree {
	
	private String ident;

	public JSVar(String ident) {
		this.ident = ident;
	}

	@Override
	public String toCode() {
		return ident;
	}

}
