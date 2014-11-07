package antlr;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import symboltable.FunctionIdentifier;
import symboltable.FunctionSymbolTable;
import symboltable.SymbolTable;
import symboltable.VariableIdentifier;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.NotUniqueIdentifierException;
import antlr.WACCParser.Char_literContext;
import antlr.WACCParser.FuncContext;
import antlr.WACCParser.ProgContext;
import antlr.WACCParser.Return_statContext;
import antlr.WACCParser.Sequential_statContext;
import antlr.WACCParser.Variable_declarationContext;

public class SemanticChecker extends WACCParserBaseVisitor<WACCType>{

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
	public WACCType visitFunc(FuncContext ctx) {
		// Checking if this function has already been defined
		String functionName = ctx.ident().getText();
		checkUniqueIdentifierRecursive(functionName, ctx);

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
	public WACCType visitReturn_stat(Return_statContext ctx) {
		WACCType exprType = visit(ctx.expr());

		//It is safe to assume that the currentSymbolTable is a FunctionSymbolTable
		FunctionSymbolTable fst = (FunctionSymbolTable) currentSymbolTable;

		// Ensuring that declared and actual return type match
		if (exprType != fst.getReturnType()) {
			throw new IncompatibleTypesException("The declared return type does not match the actual return type of the function.", ctx);
		}
		return super.visitReturn_stat(ctx);
	}

	@Override
	public WACCType visitSequential_stat(Sequential_statContext ctx) {
		for(ParseTree t : ctx.children){
			visit(t);
		}
		return super.visitSequential_stat(ctx);
	}

	@Override
	public WACCType visitProg(ProgContext ctx) {
		// First we visit all functions
		for(FuncContext funcTree:ctx.func()){
			visit(funcTree);
		}

		// Then we visit the statements
		visit(ctx.stat());

		return super.visitProg(ctx);
	}

	@Override
	public WACCType visitVariable_declaration(Variable_declarationContext ctx) {
		// First we check the identifier is unique
		String varName = ctx.ident().getText();
		checkUniqueIdentifierLocal(varName, ctx);

		// We add the current var to the SymbolTable
		currentSymbolTable.add(varName, new VariableIdentifier(ctx));

		// Compare type of lhs and rhs
		WACCType lhsType = WACCType.evalType(ctx.type());
		WACCType rhsType = visit(ctx.assign_rhs());
		if (lhsType != rhsType) {
			throw new IncompatibleTypesException("The types of the rhs and lhs of the variable declaration do not match." , ctx);
		}
		return super.visitVariable_declaration(ctx);
	}


	@Override
	public WACCType visitChar_liter(Char_literContext ctx) {
		return WACCType.CHAR;
	}

	private void checkUniqueIdentifierRecursive(String identifier, RuleContext ctx) {
		if (currentSymbolTable.containsRecursive(identifier)) {
			throw new NotUniqueIdentifierException("The identifier " + identifier + " is already in use.", ctx);
		}
	}

	private void checkUniqueIdentifierLocal(String identifier, RuleContext ctx) {
		if (currentSymbolTable.containsCurrent(identifier)) {
			throw new NotUniqueIdentifierException("The identifier " + identifier + " is already in use.", ctx);
		}
	}

}
