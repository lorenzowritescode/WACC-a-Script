package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.IntLeaf;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.MovToken;

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
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (exitVal.getType() == WACCType.INT) {
			return true;
		}
		new InvalidTypeException("Exit statements must have an int as the argument", ctx);
		return false;
	}
	
	@Override
	public TokenSequence toAssembly(Register dest) {
		TokenSequence mov = exitVal.toAssembly(dest);
		InstrToken mov2 = new MovToken(Register.R0, dest);
		InstrToken branch = new BranchLinkToken("exit");
		
		mov.appendAll(new TokenSequence(mov2, branch));
		return mov;
	}

}
