package assembly;

import org.junit.Test;

import assembly.Register;
import assembly.TokenCollector;
import assembly.TokenSequence;
import assembly.tokens.LoadStringToken;

public class LoadStringTest {
	@Test
	public void printsCorrectNumberOfQuotes() {
		LoadStringToken lst = new LoadStringToken(Register.R0, "HELLO WORLD");
		TokenCollector tc = new TokenCollector(new TokenSequence(lst));
		TokenSequence finalSeq = tc.collect();
		System.out.println(finalSeq);
	}
}
