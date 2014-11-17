package WACCExceptions;

/*
 * Used when expectations for function return types are not found/resolved
 */

@SuppressWarnings("serial")
public class UnresolvedExpectationException extends WACCException {
	
	public UnresolvedExpectationException(String message) {
		super(message);
	}

}
