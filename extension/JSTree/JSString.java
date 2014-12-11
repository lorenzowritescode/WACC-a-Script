package JSTree;

public class JSString implements JSTree {
	
	private String text;

	public JSString(String s) {
		this.text = s;
	}

	@Override
	public String toCode() {
		return text;
	}

}
