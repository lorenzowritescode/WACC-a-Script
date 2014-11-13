package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

public class WhileStatNode extends StatNode {
	
	private ExprNode loopCond;
	//private StatNode doStat;
	
	public WhileStatNode(ExprNode expr/*, StatNode doStat*/) {
		this.loopCond = expr;
		//this.doStat = doStat;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (loopCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			el.record(new InvalidTypeException("While statement should have an expr of type BOOL", ctx));
			return false;
		}
	}

}
