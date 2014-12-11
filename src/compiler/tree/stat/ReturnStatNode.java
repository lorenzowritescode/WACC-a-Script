package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import WACCExceptions.IncompatibleTypesException;
import assembly.Register;
import assembly.StackAllocator;
import assembly.TokenSequence;
import assembly.tokens.MovRegToken;
import assembly.tokens.PopToken;

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
		
		TokenSequence stackTerminationSeq = StackAllocator.stackAllocator.getTermination();
		stackTerminationSeq.append(new PopToken(Register.pc));
		
		return seq.appendAll(stackTerminationSeq);
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitReturnStatNode(this);
	}

	public WACCTree getExpr() {
		return expr;
	}
	
}
