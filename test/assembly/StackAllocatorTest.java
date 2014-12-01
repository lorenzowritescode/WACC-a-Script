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
		assertThat(sa.getCounter(), is(2));
		assertThat(sa.getInitialisation().toString().trim(), is("SUB sp, sp, #8"));
		assertThat(sa.getTermination().toString().trim(), is("ADD sp, sp, #8"));
	}
	
	@Test
	public void newScopeResetsCounter() {
		sa.allocate();
		sa.allocate();
		sa.enterNewScope();
		assertThat(sa.getCounter(), is(0));
	}	
	
	@Test
	public void exitScopeResumesCounter() {
		sa.allocate();
		sa.allocate();
		assertThat(sa.getCounter(), is(2));
		sa.enterNewScope();
		assertThat(sa.getCounter(), is(0));
		sa.exitScope();
		assertThat(sa.getCounter(), is(2));
	}
	
	@Test
	public void stackPosIsMultipleOfFour() {
		StackPosition p1 = sa.allocate();
		assertThat(p1.getStackIndex(), is(0));
		StackPosition p2 = sa.allocate();
		assertThat(p2.getStackIndex(), is(4));
		StackPosition p3 = sa.allocate();
		assertThat(p3.getStackIndex(), is(8));
	}
	
}
