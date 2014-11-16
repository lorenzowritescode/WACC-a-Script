package tree;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.ErrorListener;
import WACCExceptions.WACCException;

public abstract class WACCTree {
	public static ErrorListener el = WACCException.ERROR_LISTENER;
	private WACCTree parent;
	
	public abstract boolean check( SymbolTable st, ParserRuleContext ctx );
	public abstract WACCType getType();
	
	public static boolean isCorrect() {
		return el.errorCount() == 0;
	}
	
	public void setParent(WACCTree parent) {
		this.parent = parent;
	}
	
	public WACCTree getParent() {
		return parent;
	}
}
