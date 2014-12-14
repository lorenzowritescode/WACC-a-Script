package JSTree;

import java.util.List;

import JSTree.func.JSFunc;
import JSTree.stat.JSStat;

public class JSProg implements JSTree {

	private List<JSFunc> functions;
	private JSStat body;

	public JSProg(List<JSFunc> functions, JSStat body) {
		this.functions = functions;
		this.body = body;
	}

	@Override
	public String toCode() {
		String bodyString = body.toCode();
		
		String functionDecs = "";
		for (JSFunc f:functions) {
			functionDecs += "\n" + f.toCode();
		}
		
		return "(function() {\n " + bodyString + functionDecs + "})();\n";
	}

}
