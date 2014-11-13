package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class InvalidTypeException extends WACCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8741106963443543612L;

	public InvalidTypeException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
