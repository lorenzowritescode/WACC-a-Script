package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.IdentNode;
import WACCExceptions.InvalidTypeException;

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}
	
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!(en instanceof IdentNode)) {
			new InvalidTypeException("'Free' must be passed an identifier to a variable", ctx);
			return false;
		}
		return true;
	}

}
