package JSTree.func;

import JSTree.JSProg;
import JSTree.JSTree;

public class JSReadWrapper implements JSTree {
	
	private JSTree target;
	
	public JSReadWrapper(JSTree lhs) {
		this.target = lhs;
	}

	@Override
	public String toCode() {
		JSProg.DEPTH_COUNTER++;
		return "function (answer) {"
				+ target.toCode() + "= answer";
	}
	
}
