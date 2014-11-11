package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class UndeclaredVariableException extends WACCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2038560013979288674L;

	public UndeclaredVariableException(String exceptionMessage, RuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
