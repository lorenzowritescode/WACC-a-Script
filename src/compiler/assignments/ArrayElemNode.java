package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

public class ArrayElemNode extends AssignLhsNode {
	
	String ident;
	ExprNode pos;
	WACCType arrayType;

	public ArrayElemNode(String ident, ExprNode expr, WACCType type) {
		this.ident = ident;
		this.pos = expr;
		this.arrayType = type;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if(!(pos.getType() == WACCType.INT)) {
			el.record(new InvalidTypeException("Array position can only be found using an Int", ctx));
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return arrayType;
	}
	
	@Override
	public String getIdent() {
		return ident;
	}

}
