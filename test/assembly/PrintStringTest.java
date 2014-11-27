package assembly;

import org.junit.Test;

import assembly.tokens.PrintStringToken;

public class PrintStringTest {
	
	@Test
	public void itWorks() {
		InstrToken print = new PrintStringToken("Hello World");
		TokenSequence ts = new TokenSequence(print);
		TokenCollector tc = new TokenCollector(ts);
		TokenSequence prog = tc.collect();
		System.out.println(prog.toString());
	}
}
