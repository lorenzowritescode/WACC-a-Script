package JSTree.expr;


public class JSString extends JSExpr {
	
	private String text;

	public JSString(String s) {
		this.text = s;
	}

	@Override
	public String toCode() {
		return text;
	}

}
