package tree;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.ErrorListener;

public abstract class WACCTree {
	protected static ErrorListener el =  new ErrorListener();
	
	public abstract boolean check( SymbolTable st, RuleContext ctx );
	public abstract WACCType getType();
}
