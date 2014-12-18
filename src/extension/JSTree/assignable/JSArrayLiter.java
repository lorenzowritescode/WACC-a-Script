	package JSTree.assignable;

import java.util.ArrayList;
import java.util.Iterator;

import JSTree.JSTree;
import JSTree.expr.JSExpr;

public class JSArrayLiter extends JSTree {
	private ArrayList<JSExpr> exprs;
	
	public JSArrayLiter(ArrayList<JSExpr> exprs) {
		this.exprs = exprs;
	}
	
	@Override
	public String toCode() {
		String elems = "";
		Iterator<JSExpr> it = exprs.iterator();
		if (it.hasNext()) {
			elems += it.next().toCode();
		}
		while (it.hasNext()) {
			elems += ", " + it.next().toCode();
		}
		return "[" + elems + "]";
	}

}
