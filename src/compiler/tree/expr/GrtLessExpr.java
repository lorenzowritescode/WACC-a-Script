package tree.expr;

import org.antlr.v4.runtime.RuleContext;

import WACCExceptions.IncompatibleTypesException;
import symboltable.SymbolTable;
import tree.ExprNode;
import tree.type.WACCType;

public class GrtLessExpr extends ExprNode {
	
	private ExprNode lhsExpr;
	private ExprNode rhsExpr;
	
	public GrtLessExpr(ExprNode lhsExpr, ExprNode rhsExpr) {
		this.lhsExpr = lhsExpr;
		this.rhsExpr = rhsExpr;
	}
	

	@Override
	public boolean check(SymbolTable st, RuleContext ctx) {
		//ask about table
		if (lhsExpr.getType() == WACCType.BOOL
			||lhsExpr.getType() == WACCType.INT
			||rhsExpr.getType() == WACCType.BOOL
			||rhsExpr.getType() == WACCType.INT) {
			return true;
		} else {
			el.record(new IncompatibleTypesException("Only integers or characters can be compared using <, <=, > or >=", ctx));
			return false;
		}
	}

	@Override
	public WACCType getType() {
		return null;
	}

}
