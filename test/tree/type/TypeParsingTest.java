package tree.type;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TypeParsingTest {
	
	@Test
	public void parseBaseTypes() {
		assertThat(eval("int"), is(WACCType.INT));
	}
	
	@Test
	public void parseArrayType() {
		WACCType array = eval("int[]");
		if (!(array instanceof ArrayType))
			fail("Should be of type array");
	}
	
	@Test
	public void testParsePair() {
		WACCType pair = eval("pair(int, bool)");
		if (!(pair instanceof PairType))
			fail("Should be of type pair");
	}
	
	@Test
	public void testPairSplitString() {
		String typeString = "pair (int,bool)";
		List<String> result = Arrays.asList(typeString.split(WACCType.pairRegexSplitter));

		assertTrue(result.contains("bool"));
		assertTrue(result.contains("int"));
		assertTrue(result.contains("pair"));
	}
	
	@Test
	public void pairOfPairs() {
		String typeString = "pair(pair, pair)";
		List<String> res = Arrays.asList(typeString.split(WACCType.pairRegexSplitter));

		WACCType pair = eval(typeString);
		if(!(pair instanceof PairType))
			fail("This should yield a pair type");
	}
	
	private WACCType eval(String s) {
		return WACCType.evalType(s);
	}

}
