package tree.func;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.WACCType;

public class ParamNode extends WACCTree {
	private WACCType type;
	private String ident;
	
	public ParamNode(WACCType t, String ident) {
		this.type = t;
		this.ident = ident;
	}

	@Override
	public boolean check(SymbolTable st) {
		return true;
	}

	@Override
	public WACCType getType() {
		return type;
	}
}
