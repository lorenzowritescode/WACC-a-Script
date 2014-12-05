package tree.expr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class IntTrimTest {
	@Test
	public void trimTest() {
		assertThat(IntLeaf.trim("000000000000000000042"), is("42"));
		assertThat(IntLeaf.trim("00000000000000000000"), is("0"));
		assertThat(IntLeaf.trim("4300000000"), is("4300000000"));
		assertThat(IntLeaf.trim("4200"), is("4200"));
		assertThat(IntLeaf.trim("004200"), is("4200"));
		assertThat(IntLeaf.trim("01"), is("1"));
	}
}
