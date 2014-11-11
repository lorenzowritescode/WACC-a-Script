package tree.func;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.WACCType;
import tree.stat.StatNode;

public class FuncDecNode extends WACCTree {

	public final WACCType returnType;
	private String funcName;
	private ParamListNode params;
	private StatNode funcBody;

	public FuncDecNode(WACCType returnType, String funcName, ParamListNode params, StatNode funcBody) {
		this(returnType, funcName);
		this.params = params;
		this.funcBody = funcBody;
	}

	public FuncDecNode(WACCType returnType, String funcName) {
		this.returnType = returnType;
		this.funcName = funcName;
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
