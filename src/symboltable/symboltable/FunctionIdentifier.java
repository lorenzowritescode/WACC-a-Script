package symboltable;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.FuncContext;
import antlr.WACCParser.TypeContext;

public class FunctionIdentifier extends Identifier {
	
	public FunctionIdentifier(RuleContext ctx) {
		super(ctx);
	}

	@Override
	public TypeContext getType() {
		FuncContext fctx = this.getSpecificContext();
		return fctx.type();
	}
	/*
	public Identifier[] getArguments(){
		FuncContext fctx = getSpecificContext();
		Param_listContext params = fctx.param_list();
		int paramcount = countParams(params);
		Identifier[] args = new Identifier[];
		
	}

	private int countParams(Param_listContext params) {
		int result = 0;
		Param_listContext currentList = params;
		while(currentList != null){
			result++;
			currentList = params.param_list()
		}
	}
	*/

	private FuncContext getSpecificContext() {
		return (FuncContext) this.ctx;
	}

}
