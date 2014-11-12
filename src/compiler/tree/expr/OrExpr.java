package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import WACCExceptions.IncompatibleTypesException;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;

public class OrExpr extends ExprNode {
	
	private ExprNode lhsExpr;
	private ExprNode rhsExpr;
	
	public OrExpr(ExprNode lhsExpr, ExprNode rhsExpr) {
		this.lhsExpr = lhsExpr;
		this.rhsExpr = rhsExpr;
	}

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		if (lhsExpr.getType() != WACCType.BOOL
			&&rhsExpr.getType() != WACCType.BOOL) {
			el.record(new IncompatibleTypesException("Only integers can be multiplied or divided", ctx));
			return false;
		} else {
			return true;
		}
	}

	@Override
	public WACCType getType() {
		return WACCType.BOOL;
	}

}
