package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.ArrayType;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

public class ArrayElemNode extends ExprNode implements AssignLhsNode {
	
	String ident;
	ArrayList<ExprNode> locations;
	ArrayType arrayType;

	public ArrayElemNode(ArrayList<ExprNode> locations, ArrayType type) {
		this.locations = locations;
		this.arrayType = type;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		for(ExprNode pos : locations) {
			if(!(pos.getType() == WACCType.INT)) {
				el.record(new InvalidTypeException("Array position can only be found using an Int", ctx));
				return false;
			}
		}
		
		return true;
	}

	@Override
	public WACCType getType() {
		return arrayType.getBaseType();
	}
	
	
	public String getIdent() {
		return ident;
	}

}
