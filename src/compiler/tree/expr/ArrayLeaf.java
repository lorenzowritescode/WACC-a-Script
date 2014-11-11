package tree.expr;


import symboltable.SymbolTable;
import tree.type.ArrayTypeNode;
import tree.type.WACCType;


public class ArrayLeaf extends ExprNode {
	
	private WACCType baseType;
	private String ident;
	
	public ArrayLeaf(String ident, WACCType baseType) {
		this.baseType = baseType;
	}
	
	@Override
	public boolean check(SymbolTable st) {
		return true;
	}

	@Override
	public WACCType getType() {
		return new ArrayTypeNode(baseType);
	}
	
	public String getIdent() {
		return ident;
	}


}
