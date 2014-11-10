package tree;

import symboltable.SymbolTable;

public class FuncDecNode extends WACCTree {
	
	public final WACCType returnType;
	private String funcName;
	private ParamListNode params;
	private StatNode funcBody;
	
	public FuncDecNode(WACCType returnType, String funcName, ParamListNode params, StatNode funcBody) {
		this.returnType = returnType;
		this.funcName = funcName;
		this.params = params;
		this.funcBody = funcBody;
	}

	@Override
	public boolean check(SymbolTable st) {
		// TODO we expect the function to already be in the TOP SymbolTable
		return false;
	}

	@Override
	public WACCType getType() {
		return this.returnType;
	}

}
