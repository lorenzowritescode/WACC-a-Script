package JSTree.expr;


public class JSChar extends JSExpr {
	
	private String text;
	
	public JSChar(String text) {
		this.text = text;
	}

	@Override
	public String toCode() {
		return text;
	}

	public String getText() {
		return text;
	}

}
