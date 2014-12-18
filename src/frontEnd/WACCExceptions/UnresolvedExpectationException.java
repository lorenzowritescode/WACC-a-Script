package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Used when expectations for function return types are not found/resolved
 */

@SuppressWarnings("serial")
public class UnresolvedExpectationException extends WACCException {
	
	public UnresolvedExpectationException(String message, ParserRuleContext ctx) {
		super(message, ctx);
	}

}
