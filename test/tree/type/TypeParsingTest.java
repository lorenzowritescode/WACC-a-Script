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
	
	@Test
	public void canParseACharArray() {
		WACCType charA = WACCType.evalType("char[]");
		if( !(charA instanceof ArrayType)) {
			fail("This should yield an array type");
		}
		
		assertThat(charA.toString(), is("array-char"));
	}
	
	@Test
	public void canParseABoolArray() {
		WACCType charA = WACCType.evalType("bool[]");
		if( !(charA instanceof ArrayType)) {
			fail("This should yield an array type");
		}
		
		assertThat(charA.toString(), is("array-bool"));
	}
	
	@Test
	public void regexSplitsPairOfArrays() {
		String typeString = "pair(int[], char[])";
		List<String> res = Arrays.asList(typeString.split(WACCType.pairRegexSplitter));
		assertThat(res.size(), is(3));
		
		String fstString = res.get(1);
		assertThat(fstString, is("int[]"));
		WACCType fstType = WACCType.evalType(fstString);
		if(!(fstType instanceof ArrayType)) {
			fail("Should have parsed an ArrayType.");
		}
		
		String sndString = res.get(2);
		assertThat(sndString, is("char[]"));
		WACCType sndType = WACCType.evalType(sndString);
		if(!(sndType instanceof ArrayType)) {
			fail("Should have parsed an ArrayType.");
		}
	}
	
	@Test
	public void parsePairOfArrays() {
		WACCType arrayPair = WACCType.evalType("pair(int[], char[])");
		if ( !(arrayPair instanceof PairType)) {
			fail("This should have parsed a PairType, type produced: " + arrayPair.toString());
		}
	}
	
	@Test
	public void parseArrayOfPairs() {
		String arrayOfPairs = "pair(int,int)[]";
		
	}
	
	private WACCType eval(String s) {
		return WACCType.evalType(s);
	}

}
