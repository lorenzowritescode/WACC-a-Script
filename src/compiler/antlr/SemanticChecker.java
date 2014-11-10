package antlr;

import org.antlr.v4.runtime.tree.ParseTree;

import symboltable.SymbolTable;
import tree.CharLeaf;
import tree.FuncDecNode;
import tree.IntLeaf;
import tree.ReturnStatNode;
import tree.SeqStatNode;
import tree.StatNode;
import tree.VarDecNode;
import tree.WACCTree;
import tree.WACCType;
import antlr.WACCParser.Char_literContext;
import antlr.WACCParser.FuncContext;
import antlr.WACCParser.Int_literContext;
import antlr.WACCParser.ParamContext;
import antlr.WACCParser.ProgContext;
import antlr.WACCParser.Return_statContext;
import antlr.WACCParser.Sequential_statContext;
import antlr.WACCParser.Variable_declarationContext;
import tree.ParamListNode;
import tree.ParamNode;

public class SemanticChecker extends WACCParserBaseVisitor<WACCTree>{

	private ParseTree parseTree;
	private SymbolTable currentSymbolTable;

	public SemanticChecker(ParseTree t){
		this.parseTree = t;
		this.currentSymbolTable = new SymbolTable();
	}

	public void init() {
		parseTree.accept(this);
	}

	@Override
	public WACCTree visitFunc(FuncContext ctx) {
		ParamListNode params = new ParamListNode();
		for (ParamContext p : ctx.param_list().param()){
			ParamNode pn = (ParamNode) visit(p);
			pn.check(currentSymbolTable);
			params.add(pn);
		}
		
		currentSymbolTable = new SymbolTable(currentSymbolTable);
		
		StatNode funcBody = (StatNode) visit(ctx.stat());
		funcBody.check(currentSymbolTable);
		
		currentSymbolTable = currentSymbolTable.getParent();
		WACCType returnType = WACCType.evalType(ctx.type());
		String funcName = ctx.ident().getText();
		return new FuncDecNode(returnType, funcName, params, funcBody);
	}

	@Override
	public WACCTree visitReturn_stat(Return_statContext ctx) {
		WACCTree exprType = visit(ctx.expr());
		exprType.check(currentSymbolTable);
		
		ReturnStatNode rst = new ReturnStatNode(exprType);
		rst.check(currentSymbolTable);
		return rst;
	}

	@Override
	public WACCTree visitSequential_stat(Sequential_statContext ctx) {
		StatNode lhs = (StatNode) visit(ctx.stat(0));
		lhs.check(currentSymbolTable);
		StatNode rhs = (StatNode) visit(ctx.stat(1));
		rhs.check(currentSymbolTable);
		return new SeqStatNode(lhs, rhs);
	}

	@Override
	public WACCTree visitProg(ProgContext ctx) {
		// First we visit all functions
		for(FuncContext funcTree:ctx.func()){
			visit(funcTree);
		}

		// Then we visit the statements
		visit(ctx.stat());

		return super.visitProg(ctx);
	}

	@Override
	public WACCTree visitVariable_declaration(Variable_declarationContext ctx) {
		WACCTree rhsTree = visit(ctx.assign_rhs());
		VarDecNode vcd = new VarDecNode(ctx, rhsTree);
		vcd.check(currentSymbolTable);
		return vcd;
	}


	@Override
	public WACCTree visitChar_liter(Char_literContext ctx) {
		CharLeaf charleaf = new CharLeaf(ctx.getText());
		charleaf.check(currentSymbolTable);
		return charleaf;
	}

	@Override
	public IntLeaf visitInt_liter(Int_literContext ctx) {
		int value = Integer.parseInt(ctx.getText());
		return new IntLeaf(value);
	}

}
