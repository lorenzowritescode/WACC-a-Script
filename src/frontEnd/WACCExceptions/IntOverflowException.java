package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class IntOverflowException extends WACCException {

	public IntOverflowException(String string, ParserRuleContext ctx) {
		super(string, ctx);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
