package tree;

import java.util.ArrayList;

public class ParamListNode {
	ArrayList<ParamNode> params;
	
	public ParamListNode() {
		this.params = new ArrayList<>();
	}
	
	public void add(ParamNode pn) {
		params.add(pn);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ParamListNode) {
			ParamListNode pln = (ParamListNode) other;
			// TODO implement logic for param list equality
		}
		return false;
	}
}
