package JSTree.expr;


public class JSBool extends JSExpr {

	private boolean value;

	public JSBool(boolean value) {
		this.value = value;
	}

	@Override
	public String toCode() {
		return String.valueOf(value);
	}

}
