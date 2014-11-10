package tree;

import java.util.ArrayList;
import java.util.Iterator;

public class ParamListNode implements Iterable<ParamNode>{
	ArrayList<ParamNode> params;
	
	public ParamListNode() {
		this.params = new ArrayList<>();
	}
	
	public void add(ParamNode pn) {
		params.add(pn);
	}
	
	public ParamNode get(int i) {
		return params.get(i);
	}
	
	public int size() {
		return params.size();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ParamListNode) {
			ParamListNode pln = (ParamListNode) other;
			if(pln.size() != params.size()) {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				if ( !this.get(i).equals(pln.get(i)) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Iterator<ParamNode> iterator() {
		return params.iterator();
	}
}
