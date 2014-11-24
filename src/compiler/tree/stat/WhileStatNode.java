package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

/**
 * Class to represent while statements.
 * Rule: 'while' expr 'do' stat 'done'
 * 
 */

public class WhileStatNode extends StatNode {
	
	private ExprNode loopCond;
	
	public WhileStatNode(ExprNode expr) {
		this.loopCond = expr;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (loopCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			new InvalidTypeException("While statement should have an expr of type BOOL", ctx);
			return false;
		}
	}

}
