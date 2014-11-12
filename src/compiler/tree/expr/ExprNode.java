package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import assignments.AssignRhsNode;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public abstract class ExprNode extends AssignRhsNode  {

	//Abstract class for all expressions to extend
	
	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		return false;
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
