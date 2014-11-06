package antlr;

import org.antlr.v4.runtime.tree.ParseTree;

import antlr.WACCParser.FuncContext;
import antlr.WACCParser.Variable_assigmentContext;
import antlr.WACCParser.Variable_declarationContext;

public class SemanticChecker extends WACCParserBaseVisitor<Void>{
	
	private ParseTree waccTree;
	
	public SemanticChecker(ParseTree t){
		this.waccTree = t;
	}
	
	public void init() {
		waccTree.accept(this);
	}

}
