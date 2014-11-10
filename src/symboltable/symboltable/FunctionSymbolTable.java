package symboltable;

import antlr.WACCParser.FuncContext;
import antlr.WACCType;

public class FunctionSymbolTable extends SymbolTable {

	private FuncContext ctx;
	
	public FunctionSymbolTable(SymbolTable parent, FuncContext ctx) {
		super(parent);
		this.ctx = ctx;
	}
	
	public WACCType getReturnType() {
		return WACCType.evalType(ctx.type());		
	}
	
	// TODO: add all function parameters to symboltable

}
