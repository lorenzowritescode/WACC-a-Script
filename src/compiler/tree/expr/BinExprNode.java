package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCBinOp;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

/* Represents a Binary Operator expression
 * Holds the operator and the expressions
 * Checks expression types are compatible with the operator
 * Rule: expr binary-oper expr
 * Where binary-oper is '*'|'/'|'%'|'+'|'-'|'>'|'>='|'<'|'<='|'=='|'!='|'&&'|'||'
 */

public class BinExprNode extends ExprNode {
	
	private ExprNode lhs;
	private WACCBinOp operator;
	private ExprNode rhs;

	public BinExprNode(ExprNode lhs, WACCBinOp operator, ExprNode rhs) {
		this.lhs = lhs;
		this.operator = operator;
		this.rhs = rhs;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		if (!operator.check(lhs, rhs)) {
			new InvalidTypeException("The types in the Binary expression are not compatible.", ctx);
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return operator.getType();
	}

}
