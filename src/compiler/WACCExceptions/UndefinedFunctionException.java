package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class UndefinedFunctionException extends WACCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5005751228404799704L;

	public UndefinedFunctionException(String exceptionMessage, RuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
