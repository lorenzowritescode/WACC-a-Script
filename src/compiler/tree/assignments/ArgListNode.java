package tree.assignments;

import java.util.ArrayList;
import java.util.Iterator;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.type.WACCType;

/* Represents a list of arguments for the call to a function
 * Used to perform checks on provided arguments with the parameter list
 * declared for the given functions
 */

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
		Iterator<ExprNode> argIter = iterator();
		Iterator<ParamNode> paramIter = params.iterator();
		while(argIter.hasNext()) {
			WACCType argType = argIter.next().getType();
			WACCType paramType = paramIter.next().getType();
			if (!argType.isCompatible(paramType)) {
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
				WACCType otherType = aln.get(i).getType();
				if ( !this.get(i).getType().isCompatible((otherType)) ) {
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
