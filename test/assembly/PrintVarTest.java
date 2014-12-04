package assembly;

import org.junit.Test;

import tree.expr.VarNode;
import tree.stat.PrintLnStat;
import tree.type.WACCType;

public class PrintVarTest {

	// TODO: implement the variables underneath as JMock objects
	@Test
	public void assemblyOfMultiplePrintVarStatementIsCorrect() {
		VarNode varx = new VarNode(WACCType.INT, "x");
		varx.setPos(new StackPosition(0));
		VarNode vary = new VarNode(WACCType.INT, "y");
		vary.setPos(new StackPosition(1));
		VarNode varz = new VarNode(WACCType.INT, "z");
		varz.setPos(new StackPosition(2));
		PrintLnStat prnt = new PrintLnStat(vary);
		PrintLnStat prnt2 = new PrintLnStat(varz);
		Register r = new Register();
		TokenSequence tokSeq = prnt.toAssembly(r).appendAll(prnt2.toAssembly(r));
		TokenCollector tc = new TokenCollector(tokSeq);
		TokenSequence fin = tc.collect();
		System.out.println(fin.toString());
	}
}
