package assembly;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StackAllocatorTest {

	private StackAllocator sa = new StackAllocator();
	
	@Test
	public void noAllocationsProduceNoTokens() {
		assertThat(sa.getInitialisation().toString(), is(""));
		assertThat(sa.getTermination().toString(), is(""));
	}
	
	@Test
	public void allocationsAreCounted() {
		sa.allocate();
		sa.allocate();
		assertThat(sa.getDepth(), is(2));
		assertThat(sa.getInitialisation().toString(), is("SUB sp, sp, #8"));
		assertThat(sa.getTermination().toString(), is("ADD sp, sp, #8"));
	}
	
	@Test
	public void newScopeResetsCounter() {
		sa.allocate();
		sa.allocate();
		sa = sa.enterNewScope();
		assertThat(sa.getDepth(), is(0));
	}	
	
	@Test
	public void exitScopeResumesCounter() {
		sa.allocate();
		sa.allocate();
		assertThat(sa.getDepth(), is(2));
		sa = sa.enterNewScope();
		assertThat(sa.getDepth(), is(0));
		sa = sa.exitScope();
		assertThat(sa.getDepth(), is(2));
	}
	
}
