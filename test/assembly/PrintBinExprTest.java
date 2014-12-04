package assembly;

import java.util.ArrayList;

import org.junit.Test;

import tree.ProgNode;
import tree.expr.BinExprNode;
import tree.expr.IntLeaf;
import tree.func.FuncDecNode;
import tree.stat.PrintLnStat;
import tree.type.WACCBinOp;

public class PrintBinExprTest {

	
	@Test
	public void assemblyOfBinaryExprsAreCorrect() {
		IntLeaf intLeaf = new IntLeaf("10");
		IntLeaf intLeaf2 = new IntLeaf("8");
		WACCBinOp op = WACCBinOp.evalBinOp("*");
		BinExprNode expr = new BinExprNode(intLeaf, op, intLeaf2);
		PrintLnStat prnt = new PrintLnStat(expr);
		ProgNode prog = new ProgNode((new ArrayList<FuncDecNode>()), prnt);
		Register r = new Register();
		TokenSequence tokSeq = prog.toAssembly(r);
		TokenCollector tc = new TokenCollector(tokSeq);
		TokenSequence fin = tc.collect();
		System.out.println(fin.toString());
	}
}
