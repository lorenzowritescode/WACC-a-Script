package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public abstract class AssignLhsNode extends Assignable {

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a Assign LHS Node.");
	}
	
	public String getIdent() {
		throw new UnsupportedOperationException("It's not possible to call getIdent() on a Assign LHS Node.");
	}

}
