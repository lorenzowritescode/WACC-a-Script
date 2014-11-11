package tree.stat;

import symboltable.SymbolTable;
import tree.ExprNode;
import tree.WACCType;

public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check( SymbolTable st ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		return false;
	}

}
