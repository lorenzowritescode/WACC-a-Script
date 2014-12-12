package JSTree.func;

import java.util.Iterator;
import java.util.List;

import JSTree.JSTree;
import JSTree.expr.JSExpr;
import JSTree.stat.JSStat;

public class JSFunc implements JSTree {
	
	private String functionName;
	private JSStat functionBody;
	private JSParamList params;
	
	public JSFunc(String ident, JSParamList params, JSStat body) {
		this.functionBody = body;
		this.params = params;
		this.functionName = ident;
	}

	@Override
	public String toCode() {
		return "function " + functionName + params.toCode() + " {"
				+ functionBody.toCode()
				+ "}";
	}
	
	public static String encodeArgs(List<? extends JSTree> args) {
		String argString ="(";
		Iterator<? extends JSTree> iter = args.iterator();
		while(iter.hasNext()) {
			argString += iter.next().toCode();
			if(iter.hasNext())
				argString += ", ";
		}
		
		return argString + ")";
	}

}
