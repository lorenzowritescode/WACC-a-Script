package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 *  Used when types in variable assignments etc. are not compatible
 */

@SuppressWarnings("serial")
public class IncompatibleTypesException extends WACCException {
	
	public IncompatibleTypesException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	public IncompatibleTypesException(String message) {
		super(message);
	}

}
