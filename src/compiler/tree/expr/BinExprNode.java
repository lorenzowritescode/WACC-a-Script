package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCArithBinOp;
import tree.type.WACCBinOp;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.AddWithFlagToken;
import assembly.tokens.OverflowToken;

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

	@Override
	public int weight() {
		//TODO: ???
		return Math.min(
				Math.max(lhs.weight() + 1, rhs.weight()),
				Math.max(lhs.weight(), rhs.weight() + 1)
		);
	}

	@Override
	public TokenSequence printAssembly(Register r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public TokenSequence toAssembly(Register r) {
		TokenSequence exprs = lhs.toAssembly(r);
		exprs.appendAll(rhs.toAssembly(r.getNext()));
		if (operator instanceof WACCArithBinOp) {
			//TODO: this is hard...
		}
		
		InstrToken add = new AddWithFlagToken("s", r, r, r.getNext());
		InstrToken overflow = new OverflowToken();
		exprs.append(add);
		exprs.append(overflow);
		return exprs;
	}

}
