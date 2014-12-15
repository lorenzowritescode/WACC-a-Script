package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Abstract class used to group together all WACCTree statement nodes
 * 
 */


import symboltable.Expectation;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public abstract class StatNode extends WACCTree {

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}


	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("It's not possible to call getType() on a StatNode.");
	}
	
	public boolean checkExpectation(Expectation ec) {
		return false;
	}

}
