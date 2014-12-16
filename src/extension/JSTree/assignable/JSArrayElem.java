package JSTree.assignable;

import java.util.ArrayList;

import JSTree.expr.JSExpr;
import JSTree.expr.JSVar;

public class JSArrayElem implements JSExpr {
	
	private ArrayList<JSExpr> locations;
	private JSVar var;
	private String typeString;

	public JSArrayElem(ArrayList<JSExpr> locations, JSVar var, String typeString) {
		this.locations = locations;
		this.var = var;
		this.typeString = typeString;
	}

	@Override
	public String toCode() {
		String output = var.toCode() + "["
				+ locations.get(0).toCode() + "]";
		if (locations.size() == 2) {
			output += "[" + locations.get(1).toCode() + "]";
		}
		return output;
	}

	public ArrayList<JSExpr> getLocations() {
		return locations;
	}

	public String getType() {
		return typeString;
	}

}
