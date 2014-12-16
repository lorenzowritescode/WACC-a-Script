package JSTree;

import java.util.List;

import JSTree.func.JSFunc;
import JSTree.stat.JSStat;

public class JSProg implements JSTree {
	public static int DEPTH_COUNTER = 0;

	private List<JSFunc> functions;
	private JSStat body;

	public JSProg(List<JSFunc> functions, JSStat body) {
		this.functions = functions;
		this.body = body;
	}

	@Override
	public String toCode() {
		String requireCore = "var core = require('./src/extension/raw-js-lib/core.js');\n";
		String bodyString = body.toCode();
		
		String functionDecs = "";
		for (JSFunc f:functions) {
			functionDecs += "\n" + f.toCode();
		}
		
		String result = requireCore + "(function() {\n " + bodyString;
		result += "\ncore.terminate();\n";
		for(int i = 0; i < DEPTH_COUNTER; i++) {
			result += "})\n";
		}
		
		result += functionDecs + "})();\n";
		
		return result;
	}

}
