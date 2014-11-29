package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import assembly.Register;
import assembly.TokenSequence;

/**
 * Class to represent if statements 
 * Rule: 'if' expr 'then' stat 'else' stat 'fi'
 *
 */

public class IfStatNode extends StatNode {
	
	private ExprNode ifCond;
	private StatNode thenStat;
	private StatNode elseStat;
	
	public IfStatNode(ExprNode expr, StatNode thenStat, StatNode elseStat) {
		this.ifCond = expr;
		this.thenStat = thenStat;
		this.elseStat = elseStat;
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (ifCond.getType() == WACCType.BOOL) {
			return true;
		} else {
			new InvalidTypeException("If statements should have an expr of type BOOL", ctx);
		    return false;
		}
	}
	
	public TokenSequence toAssembly(Register register) {
		TokenSequence cond = ifCond.toAssembly(register);
		TokenSequence then = thenStat.toAssembly(register); /*register use?*/
		TokenSequence els  = elseStat.toAssembly(register);
		return null;
		//TODO: for sam: finish this
	}

}
