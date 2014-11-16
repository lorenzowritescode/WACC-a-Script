package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.IncompatibleTypesException;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.FuncDecNode;
import tree.type.WACCType;

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
}
