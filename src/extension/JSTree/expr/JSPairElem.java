package JSTree.expr;

import tree.assignments.PairElemNode.ORDER;

public class JSPairElem extends JSExpr {
	
	private JSExpr expr;
	private ORDER order;
	
	public JSPairElem(JSExpr expr, ORDER order) {
		this.expr = expr;
		this.order = order;
		
	}

	@Override
	public String toCode() {
		return expr.toCode() + "." + order.toString();
	}

}
