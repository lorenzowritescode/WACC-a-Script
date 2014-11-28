package assembly;

import org.junit.Test;

import tree.expr.IntLeaf;
import tree.stat.ExitStat;

public class ExitStatTest {

	@Test
	public void assemblyOfExitStatementsIsCorrect() {
		IntLeaf expr = new IntLeaf("5");
		ExitStat stat = new ExitStat(expr);
		Register dest = new Register();
		TokenSequence tokSeq = stat.toAssembly(dest);
		TokenCollector tc = new TokenCollector(tokSeq);
		TokenSequence fin = tc.collect();
		System.out.println(fin.toString());
	}
}
