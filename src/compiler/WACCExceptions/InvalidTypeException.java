package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("serial")
public class InvalidTypeException extends WACCException {
	
/*
 * Used when variables are assigned values which do not correspond to their given type
 */
	
	public InvalidTypeException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public InvalidTypeException(String message) {
		super(message);
	}

}
