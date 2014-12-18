package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import WACCExceptions.InvalidTypeException;
import assembly.ImmValue;
import assembly.LabelCounter;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.BranchToken;
import assembly.tokens.CompareToken;
import assembly.tokens.LabelToken;

/**
 * Class to represent while statements.
 * Rule: 'while' expr 'do' stat 'done'
 * 
 */

public class WhileStatNode extends StatNode {
	
	private ExprNode loopCond;
	private StatNode loopBody;
	
	public WhileStatNode(ExprNode expr, StatNode stat) {
		this.loopCond = expr;
		this.loopBody = stat;
	}
	
	@Override
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx) {
		if (loopCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			new InvalidTypeException("While statement should have an expr of type BOOL", ctx);
			return false;
		}
	}
	
	public TokenSequence toAssembly(Register register) {
		String l0 = "l" + LabelCounter.counter.getLabel();
		String l1 = "l" + LabelCounter.counter.getLabel();
		TokenSequence whileStat = new TokenSequence(
				new BranchToken(l0),
				new LabelToken(l1));
		whileStat.appendAll(loopBody.toAssembly(register));
		whileStat.append(
				new LabelToken(l0));
		whileStat.appendAll(loopCond.toAssembly(register));
		whileStat.appendAll(new TokenSequence(
				new CompareToken(register, ImmValue.one),
				new BranchToken("EQ", l1)));		
		return whileStat;
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitWhileStatNode(this);
	}

	public ExprNode getLoopCond() {
		return loopCond;
	}

	public StatNode getLoopBody() {
		return loopBody;
	}
	
	@Override
	public boolean checkExpectation() {
		return loopBody.checkExpectation();
	}

	@Override
	public int getVarCounter() {
		return loopBody.getVarCounter();
	}
}
