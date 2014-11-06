package symboltable;

import antlr.WACCParser.FuncContext;
import antlr.WACCParser.TypeContext;

public class FunctionSymbolTable extends SymbolTable {

	private FuncContext ctx;
	
	public FunctionSymbolTable(SymbolTable parent, FuncContext ctx) {
		super(parent);
		this.ctx = ctx;
	}
	
	public TypeContext getReturnType() {
		return ctx.type();		
	}

}
