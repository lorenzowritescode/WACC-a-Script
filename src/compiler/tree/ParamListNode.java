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
	
	public int size() {
		return params.size();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ParamListNode) {
			ParamListNode pln = (ParamListNode) other;
			if(pln.size() == params.size()) {
				Iterator<ParamNode> thisIter  = this.iterator();
				Iterator<ParamNode> otherIter = ((ParamListNode)other).iterator();
				while(thisIter.hasNext()){
					if(thisIter.next() != otherIter.next()){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<ParamNode> iterator() {
		// TODO Auto-generated method stub
		return params.iterator();
	}
}
