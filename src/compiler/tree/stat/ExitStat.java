package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;

/**
 * Class to represent exit statements for exiting a program
 * Rule: 'exit' expr
 *
 */

public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		new InvalidTypeException("Exit statements must have an int as the argument", ctx);
		return false;
	}

}
