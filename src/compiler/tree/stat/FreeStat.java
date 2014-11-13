package tree.stat;

import org.antlr.v4.runtime.RuleContext;

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

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		if (en.getType() instanceof ArrayTypeNode) {
			ArrayLeaf array = (ArrayLeaf) en;
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
