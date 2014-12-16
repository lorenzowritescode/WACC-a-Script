package symboltable;

import tree.stat.AssignStatNode;
import tree.stat.BlockStatNode;
import tree.stat.ExitStat;
import tree.stat.FreeStat;
import tree.stat.IfStatNode;
import tree.stat.PrintLnStat;
import tree.stat.PrintStat;
import tree.stat.ReadStatNode;
import tree.stat.ReturnStatNode;
import tree.stat.SeqStatNode;
import tree.stat.SkipStatNode;
import tree.stat.StatNode;
import tree.stat.VarDecNode;
import tree.stat.WhileStatNode;
import visitor.WACCTreeBaseVisitor;

public class ExpectationChecker extends WACCTreeBaseVisitor<Boolean> {
	
	@Override
	public Boolean visitAssignStatNode(AssignStatNode node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitBlockStatNode(BlockStatNode node) {
		StatNode stat = node.getStat();
		return visit(stat);
	}

	@Override
	public Boolean visitExitStat(ExitStat node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitFreeStat(FreeStat node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitIfStatNode(IfStatNode node) {
		StatNode thenStat = node.getThenStat();
		StatNode elseStat = node.getElseStat();
		return visit(thenStat) && visit(elseStat);
	}

	@Override
	public Boolean visitPrintLnStat(PrintLnStat node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitPrintStat(PrintStat node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitReadStatNode(ReadStatNode node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitReturnStatNode(ReturnStatNode node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitSeqStatNode(SeqStatNode node) {
		StatNode firstStat = node.getLhs();
		StatNode rest = node.getRhs();
		return visit(firstStat) || visit(rest);
	}

	@Override
	public Boolean visitSkipStatNode(SkipStatNode node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitVarDecNode(VarDecNode node) {
		return node.checkExpectation();
	}

	@Override
	public Boolean visitWhileStatNode(WhileStatNode node) {
		StatNode body = node.getLoopBody();
		return visit(body);
	}

}
