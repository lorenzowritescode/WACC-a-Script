package JSTree.func;

import JSTree.expr.JSExpr;

public class JSFuncCall implements JSExpr {

	private String name;
	private JSArgList args;

	public JSFuncCall(String functionName, JSArgList args) {
		this.name = functionName;
		this.args = args;
	}

	@Override
	public String toCode() {
		return name + args.toCode();
	}

}
