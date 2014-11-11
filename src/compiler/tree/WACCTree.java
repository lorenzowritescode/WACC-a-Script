package tree;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.ErrorListener;

public abstract class WACCTree {
	protected static ErrorListener el =  new ErrorListener();
	
	public abstract boolean check( SymbolTable st );
	public abstract WACCType getType();
}
