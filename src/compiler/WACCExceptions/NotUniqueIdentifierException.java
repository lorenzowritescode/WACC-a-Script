package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotUniqueIdentifierException extends WACCException {

	public NotUniqueIdentifierException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5645623132976907968L;

}
