package symboltable;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.FuncContext;
import antlr.WACCType;

public class FunctionIdentifier extends Identifier {
	
	public FunctionIdentifier(RuleContext ctx) {
		super(ctx);
		// Extracting type
		FuncContext fctx = (FuncContext) ctx;
		identType = WACCType.evalType(fctx.type());
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

}
