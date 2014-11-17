package assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.func.FuncDecNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.InvalidTypeException;
import WACCExceptions.UndeclaredIdentifierException;

public class ArrayElemNode extends ExprNode implements AssignLhsNode {
	
	String ident;
	ArrayList<ExprNode> locations;
	WACCType arrayType;

	public ArrayElemNode(String ident, ArrayList<ExprNode> locations, WACCType type) {
		this.ident = ident;
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
		if (!st.containsRecursive(ident)) {
			throw new UndeclaredIdentifierException(ident + " hasn't been defined", ctx);
		}
		if (st.get(ident) instanceof FuncDecNode) {
			throw new IncompatibleTypesException("Cannot assign to a function", ctx);
		}
		return true;
	}

	@Override
	public WACCType getType() {
		if (arrayType == WACCType.STRING) {
			return WACCType.CHAR;
		} else {
			return arrayType;
		}
	}
	
	
	public String getIdent() {
		return ident;
	}

}
