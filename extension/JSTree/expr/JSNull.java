package JSTree.expr;

public class JSNull implements JSExpr {
	
	public JSNull() {
	}

	@Override
	public String toCode() {
		return "null";
	}

}
