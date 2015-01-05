package JSTree.stat;

import JSTree.expr.JSExpr;

public class JSExitStat extends JSStat {

	private JSExpr exitCode;

	public JSExitStat(JSExpr exitCode) {
		this.exitCode = exitCode;
	}

	@Override
	public String toCode() {
		return "core.terminate("+exitCode.toCode()+")";
	}
}
