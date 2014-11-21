package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.ArrayType;
import tree.type.WACCType;

public class ArrayLiterNode extends Assignable {

	private ArrayList<ExprNode> elems;
	private WACCType baseType;
	
	public ArrayLiterNode(ArrayList<ExprNode> elems) {
		this.elems = elems;
		if (elems.size() > 0) {
			baseType = elems.get(0).getType();
		} else {
			baseType = WACCType.NULL;
		}
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Iterates through array  and checks all elements are of the same type (i.e. baseType)
		for(int i = 0; i < elems.size(); i++) {
			if (!elems.get(i).getType().isCompatible(baseType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public WACCType getType() {
		//Base Type might be null if empty array
		return new ArrayType(baseType);
	}

}
