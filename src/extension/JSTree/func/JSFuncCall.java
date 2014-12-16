package JSTree.func;

import JSTree.expr.JSExpr;

public class JSFuncCall implements JSExpr {

	private String name;
	private JSArgList args;
	private String dependancy = "";

	public JSFuncCall(String functionName, JSArgList args) {
		this.name = functionName;
		this.args = args;
	}
	
	public JSFuncCall(String dependancy, String functionName, JSArgList args) {
		this(functionName, args);
		this.dependancy = dependancy + ".";
	}

	@Override
	public String toCode() {
		return dependancy + name + args.toCode();
	}

}
