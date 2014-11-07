package antlr;

import org.antlr.v4.runtime.tree.ParseTree;

import symboltable.FunctionIdentifier;
import symboltable.FunctionSymbolTable;
import symboltable.SymbolTable;
import WACCExceptions.NotUniqueIdentifierException;
import antlr.WACCParser.FuncContext;
import antlr.WACCParser.Return_statContext;
import antlr.WACCParser.TypeContext;

public class SemanticChecker extends WACCParserBaseVisitor<Void>{
	
	private ParseTree waccTree;
	private SymbolTable currentSymbolTable;
	
	public SemanticChecker(ParseTree t){
		this.waccTree = t;
		this.currentSymbolTable = new SymbolTable();
	}
	
	public void init() {
		waccTree.accept(this);
	}

	@Override
	public Void visitFunc(FuncContext ctx) {
		// Checking if this function has already been defined
		String functionName = ctx.ident().getText();
		checkUniqueIdentifier(functionName, currentSymbolTable);
		
		// Adding function to the symboltable
		currentSymbolTable.add( functionName, new FunctionIdentifier(ctx) );
		
		// Initiating a new Symbol Table for the function scope
		currentSymbolTable = new FunctionSymbolTable(currentSymbolTable, ctx);
		
		// Checking function body
		visit(ctx.stat());
		
		//Resuming the previous symbol table scope
		currentSymbolTable = currentSymbolTable.getParent();
		return super.visitFunc(ctx);
	}

	@Override
	public Void visitReturn_stat(Return_statContext ctx) {
		visit(ctx.expr());
		return super.visitReturn_stat(ctx);
	}

	private void checkUniqueIdentifier(String identifier, SymbolTable symbolTable) {
		if (symbolTable.containsRecursive(identifier)) {
			throw new NotUniqueIdentifierException("The identifier " + identifier + " is already in use.");
		}
	}
	

}
