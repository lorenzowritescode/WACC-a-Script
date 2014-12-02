package tree;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.ErrorListener;
import WACCExceptions.WACCException;
import assembly.Register;
import assembly.StackAllocator;
import assembly.TokenSequence;

public abstract class WACCTree {
	public static ErrorListener el = WACCException.ERROR_LISTENER;
	
	//temporarily here, for testing and working out how it works -oli
	public static StackAllocator stackAllocator = new StackAllocator();
	
	
	/**
	 * @param st The current symbol table at the time of the call.
	 * @param ctx The parser rule context associated with the object on which
	 * the method is called.
	 * @return Returns a boolean value which returns false is any semantic errors
	 * are detected in the WACCTree node on which this function is called.
	 * Returns true otherwise.
	 */
	public abstract boolean check( SymbolTable st, ParserRuleContext ctx );
	
	
	/**
	 * @return Returns the WACCType associated with the WACCTree node. 
	 * In cases where there is no clear associated type, this will return WACCType.NULL
	 */
	public abstract WACCType getType();
	
	public static boolean isCorrect() {
		return el.errorCount() == 0;
	}

	public TokenSequence toAssembly(Register register) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
