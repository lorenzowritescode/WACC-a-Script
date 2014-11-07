package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class WACCException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8463718756840085322L;

	public WACCException(String exceptionMessage, RuleContext ctx){
		super(exceptionMessage);
		printContext(ctx);
	}

	private void printContext(RuleContext ctx) {
		// TODO: Implement method for printing an error message reference
	}
}
