package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import WACCExceptions.InvalidTypeException;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.MovRegToken;

/**
 * Class to represent exit statements for exiting a program
 * Rule: 'exit' expr
 *
 */

public class ExitStat extends StatNode {
	
	private ExprNode exitVal;
	
	public ExitStat(ExprNode exitVal) {
		this.exitVal = exitVal;
	}
	
	@Override
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		new InvalidTypeException("Exit statements must have an int as the argument", ctx);
		return false;
	}
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		TokenSequence ldr = exitVal.toAssembly(dest);
		InstrToken mov2 = new MovRegToken(Register.R0, dest);
		InstrToken branch = new BranchLinkToken("exit");
		
		ldr.appendAll(new TokenSequence(mov2, branch));
		return ldr;
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitExitStat(this);
	}

	public WACCTree getExpr() {
		return exitVal;
	}

}
