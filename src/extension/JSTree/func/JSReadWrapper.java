package JSTree.func;

import JSTree.JSTree;

public class JSReadWrapper extends JSTree {
	
	private JSTree target;
	
	public JSReadWrapper(JSTree lhs) {
		this.target = lhs;
	}

	@Override
	public String toCode() {
		return "function (answer) {"
				+ target.toCode() + "= answer";
	}
	
}
