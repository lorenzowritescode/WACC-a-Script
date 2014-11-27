package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.IntLeaf;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import assembly.InstrToken;
import assembly.Register;
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

	public InstrToken toAssembly() {
		Register r4 = new Register();
		Register r0 = new Register();
		InstrToken mov = exitVal.toAssembly(r4);
		InstrToken mov2 = new MovToken(r0, r4);
		InstrToken branch = new BranchLinkToken("exit");
		mov.setNext(mov2);
		mov2.setNext(branch);
		return mov;
	}

}
