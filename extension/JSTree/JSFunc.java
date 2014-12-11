package JSTree;

public class JSFunc implements JSTree {
	
	private String functionName;
	private JSStat functionBody;
	private JSParamList params;
	
	public JSFunc(String ident, JSStat body) {
		this.functionBody = body;
		this.functionName = ident;
	}

	@Override
	public String toCode() {
		return "function " + functionName + "(" + params.toCode() + ") {"
				+ functionBody.toCode()
				+ "}";
	}

}
