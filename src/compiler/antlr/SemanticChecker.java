package antlr;

import org.antlr.v4.runtime.tree.ParseTree;

import symboltable.FunctionIdentifier;
import symboltable.SymbolTable;
import WACCExceptions.NotUniqueIdentifierException;
import antlr.WACCParser.FuncContext;
import antlr.WACCParser.Return_statContext;
import antlr.WACCParser.TypeContext;

public class SemanticChecker extends WACCParserBaseVisitor<Void>{
	
	private ParseTree waccTree;
	private SymbolTable st;
	
	public SemanticChecker(ParseTree t){
		this.waccTree = t;
		this.st = new SymbolTable();
	}
	
	public void init() {
		waccTree.accept(this);
	}

	@Override
	public Void visitFunc(FuncContext ctx) {
		// Checking if this function has already been defined
		String functionName = ctx.ident().getText();
		checkUniqueIdentifier(functionName, st);
		
		// Adding function to the symboltable
		st.add( functionName, new FunctionIdentifier(ctx) );
		
		// Checking return type matches
		TypeContext functionReturnType =  ctx.type();
		visit(ctx.stat());
		return super.visitFunc(ctx);
	}

	@Override
	public Void visitReturn_stat(Return_statContext ctx) {
		st.getCurrentFuction();
		return super.visitReturn_stat(ctx);
	}

	private void checkUniqueIdentifier(String identifier, SymbolTable symbolTable) {
		if (symbolTable.contains(identifier)) {
			throw new NotUniqueIdentifierException("The identifier " + identifier + " is already in use.");
		}
	}	

}
