package JSTree.expr;


public class JSInt implements JSExpr {
	
	private int val;
	
	public JSInt(int val) {
		this.val = val;
	}

	@Override
	public String toCode() {
		return String.valueOf(val);
	}

}
