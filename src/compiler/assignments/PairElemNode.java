package assignments;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;

public class PairElemNode extends AssignLhsNode {
	
	private ExprNode expr;
	
	//Expr here should be of type 'pairType'
	public PairElemNode(String pos, ExprNode expr) {
		this.expr = expr;
	}
	
	@Override
	public boolean check( SymbolTable st, RuleContext ctx ) {
			//TODO: Work this shiz out!
		return false;
	}
	
	

}
