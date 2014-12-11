package JSTree;

public class JSBool implements JSTree {

	private boolean value;

	public JSBool(boolean value) {
		this.value = value;
	}

	@Override
	public String toCode() {
		return String.valueOf(value);
	}

}
