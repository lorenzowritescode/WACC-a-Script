package tree.stat;

import symboltable.SymbolTable;
import tree.ExprNode;
import tree.type.WACCType;

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}

	@Override
	public boolean check(SymbolTable st) {
		//TODO Implement check for 'Free' once pairs/arrays are implemented
		return false;
	}

	@Override
	public WACCType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
