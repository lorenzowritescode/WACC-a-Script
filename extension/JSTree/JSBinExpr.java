package JSTree;

public class JSBinExpr implements JSTree {
	
	private JSTree lhs;
	private String op;
	private JSTree rhs;

	public JSBinExpr(JSTree lhs, String op, JSTree rhs) {
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}

	@Override
	public String toCode() {
		return lhs.toCode() + " " + op + " " + rhs.toCode();
	}

}
