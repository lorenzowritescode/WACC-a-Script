package WACCExceptions;

@SuppressWarnings("serial")
public class UnresolvedExpectationException extends WACCException {
	
/*
 * Used when expectations for function return types are not found/resolved
 */
	
	public UnresolvedExpectationException(String message) {
		super(message);
	}

}
