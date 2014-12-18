package JSTree.func;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSFuncCall extends JSExpr {

	private JSFunc function;
	private JSArgList args;

	public JSFuncCall(JSFunc jsFunc, JSArgList args) {
		this.function = jsFunc;
		this.args = args;
	}

	@Override
	public int depthIncremented() {
		return isAsync() ? 1 : 0;
	}
	
	public JSFunc getFunction() {
		return function;
	}

	public boolean isAsync() {
		return function.isAsync();
	}
	
	// sync case
	@Override
	public String toCode() {
		return function.getFunctionName() + args.toCode();
	}
	
	// async case
	public String toCode(JSTree lhs) {
		String argString = args.toCode();
		// "(x, y, z)" -> "(x, y, z, function() {"
		argString = argString.substring(0, argString.length() - 1);
		String res = function.getFunctionName() 
				+ argString + ", "
				+ "function(answer) {\n"
				+ lhs.toCode() + "= answer" ;
		return res;
	}
	
}
