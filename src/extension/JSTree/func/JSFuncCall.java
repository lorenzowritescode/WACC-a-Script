package JSTree.func;

import java.util.Map;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSFuncCall extends JSExpr {

	private JSFunc function;
	private JSArgList args;
	private String fName;
	private Map<String, JSFunc> symboltable;

	public JSFuncCall(JSFunc jsFunc, JSArgList args) {
		this.function = jsFunc;
		this.args = args;
		this.fName = jsFunc.getFunctionName();
	}
	
	public JSFuncCall(String jsFuncName, JSArgList args, Map<String, JSFunc> symboltable) {
		this.fName = jsFuncName;
		this.args = args;
		this.symboltable = symboltable;
	}

	@Override
	public int depthIncremented() {
		return isAsync() ? 1 : 0;
	}
	
	public JSFunc getFunction() {
		if (function == null) {
			function = symboltable.get(fName);
			if (function == null) {
				throw new RuntimeException("The function " + fName + " could not be found.");
			}
		}
		return function;
	}

	public boolean isAsync() {
		return getFunction().isAsync();
	}
	
	// sync case
	@Override
	public String toCode() {
		return fName + args.toCode();
	}
	
	// async case
	public String toCode(JSTree lhs) {
		String argString = args.toCode();
		// "(x, y, z)" -> "(x, y, z, function() {"
		argString = argString.substring(0, argString.length() - 1);
		String res = fName
				+ argString + ", "
				+ "function(answer) {\n"
				+ lhs.toCode() + "= answer" ;
		return res;
	}
	
}
