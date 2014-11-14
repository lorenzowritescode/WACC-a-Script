package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ArrayLeaf;
import tree.expr.ExprNode;
import tree.type.ArrayTypeNode;
import tree.type.WACCType;

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}

}
