package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovRegToken;

/**
 * Class to represent return statements for functions
 * Rule: 'return' expr
 */

public class ReturnStatNode extends StatNode {
	private ExprNode expr;

	public ReturnStatNode(ExprNode expr) {
		this.expr = expr;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		WACCType returnType = expr.getType();
		if( !st.checkType(returnType) ) {
			new IncompatibleTypesException("A return of type " + returnType.toString() + " was not expected.", ctx);
			return false;
		}
		return true;
	}
	
	@Override
	public TokenSequence toAssembly(Register r) {
		TokenSequence seq = new TokenSequence(expr.toAssembly(r));
		seq.append(new MovRegToken(Register.R0, r));
		return seq;
	}
	
}
