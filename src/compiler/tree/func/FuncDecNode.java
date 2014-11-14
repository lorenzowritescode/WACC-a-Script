package tree.func;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.stat.StatNode;
import tree.type.WACCType;

public class FuncDecNode extends WACCTree {

	public final WACCType returnType;
	private String funcName;
	private ParamListNode params;
	private StatNode funcBody;
	private boolean complete;

	public FuncDecNode(WACCType returnType, String funcName, ParamListNode params, StatNode funcBody) {
		this(returnType, funcName);
		this.params = params;
		this.funcBody = funcBody;
		complete = true;
	}

	public FuncDecNode(WACCType returnType, String funcName) {
		this.returnType = returnType;
		this.funcName = funcName;
		complete = false;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		// TODO we expect the function to already be in the TOP SymbolTable
		if (complete == false) return false;
		return true;
	}

	@Override
	public WACCType getType() {
		return this.returnType;
	}
	
	public String getFuncName() {
		return funcName;
	}

	public ParamListNode getParams() {
		return params;
	}

	public void addParamsStat(ParamListNode params, StatNode funcBody) {
		this.params = params;
		this.funcBody = funcBody;
		complete = true;
	}

}
