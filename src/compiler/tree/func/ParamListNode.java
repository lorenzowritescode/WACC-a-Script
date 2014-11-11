package tree.func;

import java.util.ArrayList;
import java.util.Iterator;

import WACCExceptions.NotUniqueIdentifierException;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public class ParamListNode extends WACCTree implements Iterable<ParamNode>{
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

	@Override
	public boolean check(SymbolTable st) {
		ArrayList<String> paramIdents = new ArrayList<String>();
		for (ParamNode param : params) {
			if (paramIdents.contains(param.getIdent())) {
				el.record( new NotUniqueIdentifierException(
						"A variable with identifier " + this.identifier + " was already declared", 
						this.ctx)
				);
			}
		}
		return false;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("ParamListNode has no type.");
	}
}
