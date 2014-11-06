package symboltable;

import antlr.WACCParser.FuncContext;
import antlr.WACCParser.ParamContext;
import antlr.WACCParser.Param_listContext;
import antlr.WACCParser.TypeContext;

public class FunctionIdentifier extends Identifier {
	public FunctionIdentifier(FuncContext ctx) {
		this.ctx = ctx;
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
