package symboltable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import WACCExceptions.UnresolvedExpectationException;
import tree.type.WACCType;

public class ExpectationTest {
	
	private Expectation emptyExpectation = new Expectation();
	private Expectation intExpectation = new Expectation(WACCType.INT);
	
	@Test
	public void emptyExpectationIsResolved() throws Exception {
		assertThat(emptyExpectation.isResolved(true), is(true));
	}
	
	@Test
	public void emptyExpectationFailsUponReturnStat() {
		emptyExpectation.checkType(WACCType.CHAR);
		assertThat(emptyExpectation.isResolved(true), is(false));
	}
	
	@Test
	public void multipleReturnsOnEmptyNodeShouldStillFail() {
		emptyExpectation.checkType(WACCType.CHAR);
		assertThat(emptyExpectation.isResolved(true), is(false));
		emptyExpectation.checkType(WACCType.INT);
		assertThat(emptyExpectation.isResolved(true), is(false));
	}
	
	@Test
	public void intExpectationFailsWithNoReturn() {
		try {
			intExpectation.isResolved(false);
		} catch ( UnresolvedExpectationException e) {
			return;
		}
	}
	
	@Test
	public void intExpectationSuccedesWithIntReturn() {
		intExpectation.checkType(WACCType.INT);
		assertThat(intExpectation.isResolved(true), is(true));
	}
	
	@Test
	public void multipleIntReturnsAreOK() {
		intExpectation.checkType(WACCType.INT);
		assertThat(intExpectation.isResolved(true), is(true));
		intExpectation.checkType(WACCType.INT);
		intExpectation.checkType(WACCType.INT);
		assertThat(intExpectation.isResolved(true), is(true));
	}
	
	@Test
	public void wrongReturnFails() {
		intExpectation.checkType(WACCType.BOOL);
		assertThat(intExpectation.isResolved(true), is(false));
	}
	
	@Test
	public void multipleWrongReturnsFail() {
		intExpectation.checkType(WACCType.BOOL);
		assertThat(intExpectation.isResolved(true), is(false));
		intExpectation.checkType(WACCType.BOOL);
		assertThat(intExpectation.isResolved(true), is(false));
	}
	
	@Test
	public void multipleWrongReturnsAndOneRightFail() {
		intExpectation.checkType(WACCType.BOOL);
		assertThat(intExpectation.isResolved(true), is(false));
		intExpectation.checkType(WACCType.INT);
		assertThat(intExpectation.isResolved(true), is(false));
		intExpectation.checkType(WACCType.BOOL);
		assertThat(intExpectation.isResolved(true), is(false));
	}

}

