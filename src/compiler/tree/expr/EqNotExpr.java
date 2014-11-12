package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import WACCExceptions.IncompatibleTypesException;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;

public class EqNotExpr extends ExprNode {
	
	private ExprNode lhsExpr;
	private ExprNode rhsExpr;
	
	public EqNotExpr(ExprNode lhsExpr, ExprNode rhsExpr) {
		this.lhsExpr = lhsExpr;
		this.rhsExpr = rhsExpr;
	}

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		if (lhsExpr.getType() != rhsExpr.getType()) {
			el.record(new IncompatibleTypesException("Cannot compare objects of a different type using == or !=", ctx));
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
