package assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.expr.ExprNode;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import WACCExceptions.UndeclaredVariableException;

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
		return true;
	}

	@Override
	public WACCType getType() {
		return arrayType;
	}
	
	
	public String getIdent() {
		return ident;
	}

	@Override
	public void setParent(WACCTree tree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WACCTree getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPreDef(SymbolTable st, String identName, ParserRuleContext ctx) {
		if (!st.containsRecursive(identName)) {
			throw new UndeclaredVariableException(identName + " hasn't been defined", ctx);
		}
		return true;
	}

}
