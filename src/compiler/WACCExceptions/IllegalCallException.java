package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("serial")
public class IllegalCallException extends WACCException{

	public IllegalCallException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public IllegalCallException(String message) {
		super(message);
	}
	
}
