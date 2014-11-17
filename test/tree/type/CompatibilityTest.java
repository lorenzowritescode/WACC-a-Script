package tree.type;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class CompatibilityTest {
	
	private ArrayType intArr = new ArrayType(WACCType.INT);
	private PairType nullPair = new PairType(WACCType.NULL, WACCType.NULL);
	private PairType intBoolPair = new PairType(WACCType.INT, WACCType.BOOL);
	
	@Test
	public void cantAssignNull() {
		assertFalse(intArr.isCompatible(WACCType.NULL));
	}
	
	@Test
	public void arrayInt() {
		assertTrue(intArr.isCompatible(WACCType.INT));
	}
	
	@Test
	public void cantAssignWrongType() {
		assertFalse(intArr.isCompatible(WACCType.BOOL));
	}
	
	@Test
	public void canAssignNullToPair() {
		assertTrue(nullPair.isCompatible(WACCType.NULL));
	}
	
	@Test
	public void nestedPairHasNullNullType() {
		PairType p = new PairType(intBoolPair, intBoolPair);
		PairType innerFirst = ((PairType) p.getFstType());
		assertThat(innerFirst.toString(), is(nullPair.toString()));

		PairType innerSecond = ((PairType) p.getSndType());
		assertThat(innerSecond.toString(), is(nullPair.toString()));
	}
	
	@Test
	public void nestedPair() {
		assertTrue(nullPair.isCompatible(new PairType(intBoolPair, intBoolPair)));
	}
}
