package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidTypeException extends WACCException {
	
	public InvalidTypeException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public InvalidTypeException(String message) {
		super(message);
	}

}
