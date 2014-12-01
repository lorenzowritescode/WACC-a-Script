//package assembly;
//
//import org.junit.Test;
//
//import assembly.tokens.PrintStringToken;
//
//public class PrintStringTest {
//	
//	@Test
//	public void oneString() {
//		InstrToken print = new PrintStringToken("Hello World");
//		TokenSequence ts = new TokenSequence(print);
//		printSequence(ts);
//	}
//	
//	@Test 
//	public void multipleStrings() {
//		InstrToken print = new PrintStringToken("Hello World");
//		InstrToken print2 = new PrintStringToken("Hey Gurl Hey");
//		TokenSequence ts = new TokenSequence(print, print2);
//		printSequence(ts);
//	}	
//	
//	private static void printSequence(TokenSequence ts) {
//		TokenCollector tc = new TokenCollector(ts);
//		TokenSequence prog = tc.collect();
//		System.out.println(prog.toString());
//	}
//}
