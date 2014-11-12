package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import WACCExceptions.IncompatibleTypesException;
import symboltable.SymbolTable;
import tree.ExprNode;
import tree.type.WACCType;

public class ArithExpr extends ExprNode {
	
	private ExprNode lhsExpr;
	private ExprNode rhsExpr;
	
	public ArithExpr(ExprNode lhsExpr, ExprNode rhsExpr) {
		this.lhsExpr = lhsExpr;
		this.rhsExpr = rhsExpr;
	}
	
	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		if (lhsExpr.getType() != WACCType.INT
		   ||rhsExpr.getType() != WACCType.INT) {
     		el.record(new IncompatibleTypesException("Only integers can be added or subtracted", ctx));
			return false;
		} else {
			return true;
		}
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
