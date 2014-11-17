package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;

/**
 * Class to represent if statements 
 * Rule: 'if' expr 'then' stat 'else' stat 'fi'
 *
 */

public class IfStatNode extends StatNode {
	
	private ExprNode ifCond;
	
	public IfStatNode(ExprNode expr) {
		this.ifCond = expr;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (ifCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			new InvalidTypeException("If statements should have an expr of type BOOL", ctx);
		    return false;
		}
	}
	

}
