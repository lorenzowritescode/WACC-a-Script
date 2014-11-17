package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("serial")
public class NotUniqueIdentifierException extends WACCException {

/*
 * Used when an identifier is used which is already in use by another variable
 */
	
	public NotUniqueIdentifierException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
