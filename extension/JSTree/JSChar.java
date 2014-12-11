package JSTree;

public class JSChar implements JSTree {
	
	private String text;
	
	public JSChar(String text) {
		this.text = text;
	}

	@Override
	public String toCode() {
		return text;
	}

}
