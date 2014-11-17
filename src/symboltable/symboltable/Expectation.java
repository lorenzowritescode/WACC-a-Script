package symboltable;

import WACCExceptions.IncompatibleTypesException;
import tree.type.WACCType;

/*
 * Expectation represents a scope such as the program body of a function body.
 * It is used to determine wether the return type expected has been matched.
 */
public class Expectation {
	private WACCType expectedType;
	private boolean hasBeenCalled;
	private boolean resolved;
	private boolean noReturnExpected;
	
	/** 
	 * Creates a function expectation
	 * @param expected
	 * 		The WACCType that is expected. This should match the function declared type.
	 */
	public Expectation(WACCType expected) {
		this.expectedType = expected;
		this.resolved = false;
		this.hasBeenCalled = false;
		noReturnExpected = false;
	}
	
	/**
	 * Creates an empty expectation. 
	 * Return statements that attempt to checkType() will result in an error being recorded.
	 */
	public Expectation() {
		this.expectedType = null;
		this.resolved = true;
		this.hasBeenCalled = false;
		noReturnExpected = true;
	}
	
	public boolean checkType(WACCType actualType) {
		if (noReturnExpected) {
			hasBeenCalled = true;
			new IncompatibleTypesException("No return type expected.");
			return false;
		}
		
		// TODO: here we need a stricter type of equality
		if (!hasBeenCalled) {
			hasBeenCalled = true;
			resolved = expectedType != null 
					&& actualType.isCompatible(expectedType);
		} else {
			// after the first call, `resolved` depends on its previous value
			resolved = resolved 
					&& expectedType != null 
					&& actualType.isCompatible(expectedType);
		}
		return resolved;
	}
	
	/**
	 * @return
	 * 		true iff all the expectations were met.
	 */
	public boolean isResolved() {
		// only one of hasBeenCalled and noReturnExpected should be true
		return (hasBeenCalled != noReturnExpected) && resolved;
	}
	
}
