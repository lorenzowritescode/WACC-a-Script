package tree.stat;

import WACCExceptions.InvalidTypeException;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;


public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal, ) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check( SymbolTable st ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		el.record(new InvalidTypeException("Exit statements must have an int as the argument", ctx));
		return false;
	}

}
