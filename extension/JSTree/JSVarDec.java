package JSTree;

public class JSVarDec implements JSTree {
	
	private JSExpr var;
	private JSTree rhs;
	
	public JSVarDec(JSExpr var, JSTree rhs) {
		this.var = var;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		return "var " + var.toCode() + " = " + rhs.toCode();
	}

}
