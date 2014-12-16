package tree.assignments;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import assembly.Register;
import assembly.TokenSequence;

/* Represents a list of arguments for the call to a function
 * Used to perform checks on provided arguments with the parameter list
 * declared for the given functions
 */

public class ArgListNode extends WACCTree implements Iterable<ExprNode>{
	Deque<ExprNode> args;
	
	public ArgListNode() {
		this.args = new ArrayDeque<>();
	}
	
	public void add(ExprNode expr) {
		args.add(expr);
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
		if(!(other instanceof ArgListNode))
			return false;
		
		ArgListNode aln = (ArgListNode) other;
		if(aln.size() != args.size())
			return false;
		
		Iterator<ExprNode> iter = aln.iterator();
		for (ExprNode e1:this) {
			ExprNode e2 = iter.next();
			if (!e1.getType().isCompatible(e2.getType()))
				return false;
		}
		
		return true;
	}

	@Override
	public Iterator<ExprNode> iterator() {
		return args.iterator();
	}
	
	public Iterator<ExprNode> reverseIterator() {
		return args.descendingIterator();
	}

	@Override
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("ArgListNode has no type.");
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		throw new UnsupportedOperationException(
				"ArgListNode doesn't implement toAssembly directly."
				+ "It is taken care of by CallStatNode through this class iterator.");
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitArgListNode(this);
	}
	
}
