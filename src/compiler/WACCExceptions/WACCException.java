package WACCExceptions;

import org.antlr.v4.runtime.RuleContext;

public class WACCException extends RuntimeException {
	public WACCException(String exceptionMessage, RuleContext ctx){
		super(exceptionMessage);
		printContext(ctx);
	}

	private void printContext(RuleContext ctx) {
		// TODO Auto-generated method stub
		
	}
}
