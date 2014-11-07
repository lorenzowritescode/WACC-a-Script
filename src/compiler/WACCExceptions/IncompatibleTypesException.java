package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class IncompatibleTypesException extends WACCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 554801317748591605L;

	public IncompatibleTypesException(String exceptionMessage, RuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
