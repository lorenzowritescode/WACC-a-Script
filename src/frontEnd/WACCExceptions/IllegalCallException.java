package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Used when an incorrect call is made to a function (i.e. wrong number of arguments)
 */

@SuppressWarnings("serial")
public class IllegalCallException extends WACCException{
	
	public IllegalCallException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public IllegalCallException(String message) {
		super(message);
	}
	
}
