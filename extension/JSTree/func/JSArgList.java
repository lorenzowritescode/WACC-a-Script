package JSTree.func;

import java.util.List;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSArgList implements JSTree {
	
	List<JSExpr> args;

	public JSArgList(List<JSExpr> args) {
		this.args = args;
	}

	@Override
	public String toCode() {
		String argString ="(";
		for (JSExpr e:args) {
			argString += e.toCode() + ", ";
		}
		
		return argString.substring(0, argString.length() - 2) + ")";
	}

}
