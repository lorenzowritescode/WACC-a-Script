package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;


public class AssigningExprNode extends AssignRhsNode {

	private ExprNode expr;
	
	public AssigningExprNode(ExprNode expr) {
		this.expr = expr;
	}
	
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		return expr.getType();
	}


}
