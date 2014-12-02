package assembly;

import org.junit.Test;

import tree.expr.BoolLeaf;
import tree.expr.CharLeaf;
import tree.expr.IntLeaf;
import tree.expr.StringLeaf;
import tree.stat.PrintLnStat;

public class PrintIntTest {

	
	@Test
	public void assemblyOfPrintLnStatementIsCorrect() {
		IntLeaf intLeaf = new IntLeaf("10");
		PrintLnStat prnt = new PrintLnStat(intLeaf);
		Register r = new Register();
		TokenSequence tokSeq = prnt.toAssembly(r);
		TokenCollector tc = new TokenCollector(tokSeq);
		TokenSequence fin = tc.collect();
		System.out.println(fin.toString());
	}
}
