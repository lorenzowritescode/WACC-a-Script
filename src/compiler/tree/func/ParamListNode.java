package tree.func;

import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.runtime.RuleContext;

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
	public boolean check(SymbolTable st, RuleContext ctx) {
		ArrayList<String> paramIdents = new ArrayList<String>();
		//Check whether there are duplicate comments
		for (ParamNode param : params) {
			if (paramIdents.contains(param.getIdent())) {
				el.record( new NotUniqueIdentifierException(
						"A variable with identifier " + param.getIdent() + " was already declared", 
						ctx)
				);
				return false;
			}
			
			//else add to the symbol table
			st.add(param.getIdent(), param);
			
		}
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("ParamListNode has no type.");
	}

}
