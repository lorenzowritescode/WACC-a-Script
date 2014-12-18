package JSTree.stat;

import JSTree.JSTree;
import JSTree.func.JSReadWrapper;

public class JSReadStat extends JSStat {

	private JSTree lhs;
	
	public JSReadStat(JSTree lhs) {
		this.lhs = lhs;
	}
	
	@Override
	public String toCode() {
		JSReadWrapper callback = new JSReadWrapper(lhs);
		return "core.read(" + callback.toCode();
	}

	@Override
	public int depthIncremented() {
		return 1;
	}

	@Override
	public boolean isAsync() {
		return true;
	}

}
