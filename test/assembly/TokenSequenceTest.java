package assembly;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import assembly.tokens.EmptyToken;

public class TokenSequenceTest {
	
	TokenSequence ts = new TokenSequence();
	
	@Test
	public void testAddAll() {
		ts.setAcceptAll();
		InstrToken t = new EmptyToken();
		ts.append(t);
		ts.prepend(t);
		assertThat(ts.size(), is (2));
	}
	
	@Test
	public void testAddUnique() {
		ts.setUnique();
		InstrToken t = new EmptyToken();
		ts.append(t);
		ts.prepend(t);
		ts.append(t);
		assertThat(ts.size(), is (1));
	}	

}
