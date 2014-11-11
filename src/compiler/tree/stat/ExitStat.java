package tree.stat;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.ExprNode;
import tree.type.WACCType;

public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check( SymbolTable st, RuleContext ctx ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		return false;
	}

}
