package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.ArrayTypeNode;
import tree.type.WACCType;
import assignments.ArrayElemNode;

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (en.getType() instanceof ArrayTypeNode) {
			ArrayElemNode array = (ArrayElemNode) en;
			if (!st.containsCurrent(array.getIdent())) {
				return false;
			}
		}
		return false;
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
