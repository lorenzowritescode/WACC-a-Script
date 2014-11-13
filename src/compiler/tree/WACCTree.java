package tree;

import org.antlr.v4.runtime.RuleContext;

import antlr.SemanticChecker;
import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.ErrorListener;

public abstract class WACCTree {
	public static ErrorListener el = SemanticChecker.ERROR_LISTENER;
	
	public abstract boolean check( SymbolTable st, RuleContext ctx );
	public abstract WACCType getType();
	
	public static boolean isCorrect() {
		return el.errorCount() == 0;
	}
}
