package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class InvalidTypeException extends WACCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8741106963443543612L;

	public InvalidTypeException(String exceptionMessage, RuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
