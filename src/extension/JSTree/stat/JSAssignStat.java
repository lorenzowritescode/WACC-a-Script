package JSTree.stat;

import JSTree.JSTree;
import JSTree.func.JSFuncCall;

public class JSAssignStat extends JSStat {

	private JSTree lhs;
	private JSTree rhs;

	public JSAssignStat(JSTree lhs, JSTree rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		if(rhs instanceof JSFuncCall) {
			JSFuncCall fcall = (JSFuncCall) rhs;
			if (fcall.isAsync()) {
				return fcall.toCode(lhs);
			}
		}
		return lhs.toCode() + " = " + rhs.toCode();
	}

	@Override
	public int depthIncremented() {
		return rhs.depthIncremented();
	}
	
}
