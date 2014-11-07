package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class NotUniqueIdentifierException extends WACCException {

	public NotUniqueIdentifierException(String exceptionMessage, RuleContext ctx) {
		super(exceptionMessage, ctx);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5645623132976907968L;

}
