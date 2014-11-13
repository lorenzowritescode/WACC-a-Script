package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public class AssignRhsNode extends WACCTree {

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return false;
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
