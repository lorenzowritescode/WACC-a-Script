package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("serial")
public class IncompatibleTypesException extends WACCException {
	
	public IncompatibleTypesException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public IncompatibleTypesException(String message) {
		super(message);
	}

}
