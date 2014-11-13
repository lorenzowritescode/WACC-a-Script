package assignments;

import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.type.WACCType;

public class ArgListNode extends WACCTree implements Iterable<ExprNode>{
	ArrayList<ExprNode> args;
	
	public ArgListNode() {
		this.args = new ArrayList<>();
	}
	
	public void add(ExprNode expr) {
		args.add(expr);
	}
	
	public ExprNode get(int i) {
		return args.get(i);
	}
	
	public int size() {
		return args.size();
	}
	
	//For comparisons during function calls
	public boolean compareToParamList(ParamListNode params) {
		if(!(args.size() == params.size())) {
			return false;
		}
		Iterator<ExprNode> argIter = iterator();
		Iterator<ParamNode> paramIter = params.iterator();
		while(argIter.hasNext()) {
			if (argIter.next().getType() != paramIter.next().getType()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		//Checks that types of arg lists are the same
		if(!(other instanceof ArgListNode)) {
			return false;
		}
			ArgListNode aln = (ArgListNode) other;
			if(aln.size() != args.size()) {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				if ( !this.get(i).getType().isCompatible((aln.get(i).getType())) ) {
					return false;
				}
			}
			return true;
	}

	@Override
	public Iterator<ExprNode> iterator() {
		return args.iterator();
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("ArgListNode has no type.");
	}


	
}
