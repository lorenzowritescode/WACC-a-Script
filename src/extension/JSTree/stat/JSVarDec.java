package JSTree.stat;

import JSTree.JSTree;
import JSTree.expr.JSExpr;
import JSTree.func.JSFuncCall;

public class JSVarDec extends JSStat {
	
	private JSExpr var;
	private JSTree rhs;
	
	public JSVarDec(JSExpr var, JSTree rhs) {
		this.var = var;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		if (isAsync())
			return "var x; \n" + ((JSFuncCall) rhs).toCode(var);

		return "var " + var.toCode() + " = " + rhs.toCode();
	}

	@Override
	public int depthIncremented() {
		return rhs.depthIncremented();
	}

	@Override
	public boolean isAsync() {
		if (rhs instanceof JSFuncCall) {
			JSFuncCall fCall = (JSFuncCall) rhs;
			return fCall.isAsync();
		}
		return false;
	}

}
