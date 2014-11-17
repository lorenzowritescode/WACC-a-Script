package antlr;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import symboltable.SymbolTable;
import tree.ProgNode;
import tree.WACCTree;
import tree.expr.BinExprNode;
import tree.expr.BoolLeaf;
import tree.expr.CharLeaf;
import tree.expr.ExprNode;
import tree.expr.IdentNode;
import tree.expr.IntLeaf;
import tree.expr.StringLeaf;
import tree.expr.UnExprNode;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.stat.AssignStatNode;
import tree.stat.ExitStat;
import tree.stat.FreeStat;
import tree.stat.IfStatNode;
import tree.stat.PrintLnStat;
import tree.stat.PrintStat;
import tree.stat.ReadStatNode;
import tree.stat.ReturnStatNode;
import tree.stat.SeqStatNode;
import tree.stat.SkipStatNode;
import tree.stat.StatNode;
import tree.stat.VarDecNode;
import tree.stat.WhileStatNode;
import tree.type.PairType;
import tree.type.WACCBinOp;
import tree.type.WACCType;
import tree.type.WACCUnOp;
import util.DebugHelper;
import WACCExceptions.WACCException;
import antlr.WACCParser.Arg_listContext;
import antlr.WACCParser.Array_elemContext;
import antlr.WACCParser.Array_literContext;
import antlr.WACCParser.Assign_lhsContext;
import antlr.WACCParser.Block_statContext;
import antlr.WACCParser.Bool_literContext;
import antlr.WACCParser.Char_literContext;
import antlr.WACCParser.Exit_statContext;
import antlr.WACCParser.ExprContext;
import antlr.WACCParser.Free_statContext;
import antlr.WACCParser.FuncContext;
import antlr.WACCParser.Func_call_assignmentContext;
import antlr.WACCParser.IdentContext;
import antlr.WACCParser.If_statContext;
import antlr.WACCParser.Int_literContext;
import antlr.WACCParser.Newpair_assignmentContext;
import antlr.WACCParser.Pair_elemContext;
import antlr.WACCParser.ParamContext;
import antlr.WACCParser.Param_listContext;
import antlr.WACCParser.Print_statContext;
import antlr.WACCParser.Println_exprContext;
import antlr.WACCParser.ProgContext;
import antlr.WACCParser.Read_statContext;
import antlr.WACCParser.Return_statContext;
import antlr.WACCParser.Sequential_statContext;
import antlr.WACCParser.Skip_statContext;
import antlr.WACCParser.Str_literContext;
import antlr.WACCParser.Variable_assigmentContext;
import antlr.WACCParser.Variable_declarationContext;
import antlr.WACCParser.While_statContext;
import assignments.ArgListNode;
import assignments.ArrayElemNode;
import assignments.ArrayLiterNode;
import assignments.AssignLhsNode;
import assignments.Assignable;
import assignments.CallStatNode;
import assignments.NewPairNode;
import assignments.PairElemNode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class SemanticChecker extends WACCParserBaseVisitor<WACCTree>{
	
	public static final DebugHelper dbh = new DebugHelper();
	private ParseTree parseTree;
	private SymbolTable currentSymbolTable;

	public SemanticChecker(ParseTree t) {
		this.parseTree = t;
		this.currentSymbolTable = new SymbolTable();
	}


	public void init() {
		dbh.printV("Checking sematic integrity...");
		WACCTree tree = parseTree.accept(this);
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.alias("WACCTree", WACCTree.class);
        dbh.printD(xstream.toXML(tree));
	}

	@Override
	public WACCTree visitFunc(FuncContext ctx) {
		
		// Pull out type and function name
		WACCType returnType = WACCType.evalType(ctx.type());
		String funcName = ctx.ident().getText();

		// Create an inner scope Symbol Table for the function body.
		currentSymbolTable = new SymbolTable(currentSymbolTable, returnType);
		
		// Visit the param list and get the ParamListNode
		ParamListNode params = (ParamListNode) visit(ctx.param_list());	
		
		// Add FuncNode to function scope
		FuncDecNode funcNode = new FuncDecNode(returnType, funcName, params);
		currentSymbolTable.add(funcName, funcNode);	
		
		// Create the functionBody node
		StatNode funcBody = (StatNode) visit(ctx.stat());
		
		// finalise the current symbolTable and restore the parent scope
		currentSymbolTable.finaliseScope(funcName);
		currentSymbolTable = currentSymbolTable.getParent();
		
		// Add function body statement to the function node
		funcNode.addFuncBody(funcBody);
		funcNode.check(currentSymbolTable, ctx);

		return funcNode;
	}

	@Override
	public WACCTree visitReturn_stat(Return_statContext ctx) {
		ExprNode exprType = (ExprNode) visit(ctx.expr());
		
		ReturnStatNode rst = new ReturnStatNode(exprType);
		rst.check(currentSymbolTable, ctx);

		return rst;
	}

	@Override
	public WACCTree visitSequential_stat(Sequential_statContext ctx) {
		StatNode lhs = (StatNode) visit(ctx.stat(0));
		StatNode rhs = (StatNode) visit(ctx.stat(1));;
		SeqStatNode seqStat = new SeqStatNode(lhs, rhs);
		seqStat.check(currentSymbolTable, ctx);

		return seqStat;
	}
	
	

	@Override
	public WACCTree visitPrint_stat(Print_statContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintStat ps = new PrintStat(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}
	
	@Override
	public WACCTree visitPrintln_expr(Println_exprContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PrintLnStat ps = new PrintLnStat(expr);
		ps.check(currentSymbolTable, ctx);

		return ps;
	}
	
	@Override
	public WACCTree visitRead_stat(Read_statContext ctx) {
		AssignLhsNode lhs = (AssignLhsNode) visit(ctx.assign_lhs());
		ReadStatNode rsn = new ReadStatNode(lhs);
		rsn.check(currentSymbolTable, ctx);

		return rsn;
	}

	@Override
	public WACCTree visitFree_stat(Free_statContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		FreeStat stat = new FreeStat(expr);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}

	@Override
	public WACCTree visitExit_stat(Exit_statContext ctx) {
		ExprNode exitVal = (ExprNode) visit(ctx.expr());
		ExitStat stat = new ExitStat(exitVal);
		stat.check(currentSymbolTable, ctx);

		return stat;
	}
	
	@Override
	public WACCTree visitSkip_stat(Skip_statContext ctx) {
		SkipStatNode ssn = new SkipStatNode();
		return ssn;
	}
	
	
	// TODO: remove this method -- there is no such thing as a standalone ident
	@Override
	public WACCTree visitIdent(IdentContext ctx) {
		String ident = ctx.IDENTITY().getText();
		if(currentSymbolTable.containsRecursive(ident)) {
			WACCTree var = currentSymbolTable.get(ident);
			WACCType varType = var.getType();
			IdentNode idNode = new IdentNode(varType, ident);
			idNode.check(currentSymbolTable, ctx);
			return idNode;
		}
		//If ident is not present in symboltable, and ident with a null
		//type will be returned. 
		//TODO: find a better way around this
		return new IdentNode(null, ident);
		
	}

	@Override
	public WACCTree visitProg(ProgContext ctx) {
		// First we visit all functions and register a stub of its return type
		for (FuncContext fctx : ctx.func()) {
			registerFunction(fctx);
		}
		
		ArrayList<FuncDecNode> functions = new ArrayList<>();
		// We visit all the functions and create full nodes
		for (FuncContext fctx : ctx.func()){
			FuncDecNode fdec = (FuncDecNode) visit(fctx);
			fdec.check(currentSymbolTable, ctx);
			functions.add(fdec);
		}
		
		// Then we visit the statement
		StatNode progBody = (StatNode) visit(ctx.stat());
		
		// Finally, we return the program node
		return new ProgNode(functions, progBody);
	}
	
	

	@Override
	public WACCTree visitAssign_lhs(Assign_lhsContext ctx) {
		Assignable lhs = (Assignable) visit(ctx.getChild(0));
		return lhs;
	}	

	@Override
	public WACCTree visitVariable_assigment(Variable_assigmentContext ctx) {
		AssignLhsNode lhs = (AssignLhsNode) visit(ctx.assign_lhs());
		Assignable rhs = (Assignable) visit(ctx.assign_rhs());
		AssignStatNode assignment = new AssignStatNode(lhs, rhs);
		assignment.check(currentSymbolTable, ctx);
		
		return assignment;
	}

	/**
	 * registerFunction adds a stub of the function into the currentSymbolTable without recursing into the function body.
	 * This is to support nested function calls, where a function body might call a not yet defined function.
	 * @param fctx
	 * 		The FuncContext generated by the Parser
	 */
	private void registerFunction(FuncContext fctx) {
		String funcName = fctx.ident().getText();
		WACCType returnType = WACCType.evalType(fctx.type());
		FuncDecNode funcStub = new FuncDecNode(returnType, funcName);
		currentSymbolTable.add(funcName, funcStub);
	}
	
	public WACCTree visitVariable_declaration(Variable_declarationContext ctx) {
		Assignable rhsTree = (Assignable) visit(ctx.assign_rhs());
		WACCType varType = WACCType.evalType(ctx.type());
		String ident = ctx.ident().getText();
		VarDecNode vcd = new VarDecNode(varType, ident, rhsTree);
		vcd.check(currentSymbolTable, ctx);
		
		return vcd;
	}
	
	
	
	@Override
	public WACCTree visitNewpair_assignment(Newpair_assignmentContext ctx) {
		ExprNode fst = (ExprNode) visit(ctx.expr(0));
		ExprNode snd = (ExprNode) visit(ctx.expr(1));
		NewPairNode pair = new NewPairNode(fst, snd);
		pair.check(currentSymbolTable, ctx);
		return pair;
	}

	@Override
	public WACCTree visitFunc_call_assignment(Func_call_assignmentContext ctx) {
		String ident = ctx.ident().getText();
		FuncDecNode funcDef = (FuncDecNode) currentSymbolTable.get(ident);
		ArgListNode args = (ArgListNode) visit(ctx.arg_list());
		CallStatNode callStat = new CallStatNode(funcDef, args);
		callStat.check(currentSymbolTable, ctx);
		return callStat;
	}

	@Override
	public WACCTree visitArg_list(Arg_listContext ctx) {
		int argListLength = ctx.getChildCount();
		ArgListNode args = new ArgListNode();
		for (int i=0; i<argListLength; i++) {
			args.add((ExprNode) visit(ctx.getChild(i)));
		}
		args.check(currentSymbolTable, ctx);
		return args;
	}

	@Override
	public WACCTree visitPair_elem(Pair_elemContext ctx) {
		ExprNode expr = (ExprNode) visit(ctx.expr());
		PairType type = (PairType) expr.getType();
		String ident = ((IdentNode) expr).getIdent();
		WACCType innerType;
		if (ctx.FST() != null) {
			innerType = type.getFst();
		} else {
			innerType = type.getSnd();
		}
		PairElemNode pairElem = new PairElemNode(expr, ident, innerType);
		pairElem.check(currentSymbolTable, ctx);
		return pairElem;
	}
	
	@Override
	public WACCTree visitArray_liter(Array_literContext ctx) {
		ArrayList<ExprNode> elems = new ArrayList<ExprNode>();
		
		for(int i=0; i< ctx.expr().size() ; i++) {
			elems.add((ExprNode) visit(ctx.expr(i)));
		}
		
		ArrayLiterNode arrayLiter = new ArrayLiterNode(elems);
		arrayLiter.check(currentSymbolTable, ctx);
		return arrayLiter;
	}
	
	@Override
	public WACCTree visitArray_elem(Array_elemContext ctx) {
		String ident = ctx.ident().getText();
		List<ExprContext> exprCtxs =  ctx.expr();
		ArrayList<ExprNode> exprs = new ArrayList<ExprNode>();
		for (ExprContext ec : exprCtxs) {
			exprs.add((ExprNode) visit(ec));
		}
		WACCType type = currentSymbolTable.get(ident).getType();
		ArrayElemNode arrayElem = new ArrayElemNode(ident, exprs, type); 
		return arrayElem;
	}

	@Override
	public WACCTree visitChar_liter(Char_literContext ctx) {
		CharLeaf charleaf = new CharLeaf(ctx.getText());
		charleaf.check(currentSymbolTable, ctx);
		return charleaf;
	}

	@Override
	public WACCTree visitInt_liter(Int_literContext ctx) {
		String intValue = ctx.getText();
		IntLeaf intLeaf = new IntLeaf(intValue);
		intLeaf.check(currentSymbolTable, ctx);
		return intLeaf;
	}

	@Override
	public WACCTree visitBool_liter(Bool_literContext ctx) {
		BoolLeaf boolLeaf = new BoolLeaf(ctx.getText());
		boolLeaf.check(currentSymbolTable, ctx);
		return boolLeaf;
	}

	@Override
	public WACCTree visitStr_liter(Str_literContext ctx) {
		StringLeaf strLeaf = new StringLeaf(ctx.getText());
		strLeaf.check(currentSymbolTable, ctx);
		return strLeaf;
	}

	@Override
	public WACCTree visitParam(ParamContext ctx) {
		WACCType paramType = WACCType.evalType(ctx.type());
		String ident = ctx.ident().getText();
		ParamNode paramNode = new ParamNode(paramType, ident);
		paramNode.check(currentSymbolTable, ctx);
		return paramNode;
	}

	@Override
	public WACCTree visitParam_list(Param_listContext ctx) {
		ParamListNode params = new ParamListNode();
		for (ParamContext p : ctx.param()){
			ParamNode pn = (ParamNode) visit(p);
			params.add(pn);
		}
		params.check(currentSymbolTable, ctx);
		return params;
	}

	@Override
	public WACCTree visitWhile_stat(While_statContext ctx) {
		ExprNode loopCond = (ExprNode) visit(ctx.expr());
		WhileStatNode whileStat = new WhileStatNode(loopCond);
		whileStat.check(currentSymbolTable, ctx);
		
		return whileStat;
	}

	@Override
	public WACCTree visitIf_stat(If_statContext ctx) {
		ExprNode ifCond = (ExprNode) visit(ctx.expr());
		IfStatNode ifStat = new IfStatNode(ifCond);
		ifStat.check(currentSymbolTable, ctx);
		
		return ifStat;
	}

	@Override
	public WACCTree visitExpr(ExprContext ctx) {
		// if it's an atomic `( expr )` expression, we just call visit on the inner expr
		if (ctx.OPEN_PARENTHESES() != null) {
			assert(ctx.children.size() == 3);
			return visit(ctx.expr(0));
		}
		
		switch (ctx.getChildCount()) {
		case 3: // Binary Expression of type `lhs OP rhs`
			ExprNode lhs = (ExprNode) visit(ctx.expr(0));
			ExprNode rhs = (ExprNode) visit(ctx.expr(1));
			WACCBinOp binaryOp = WACCBinOp.evalBinOp(ctx.getChild(1).getText());
			BinExprNode binExpr = new BinExprNode(lhs, binaryOp, rhs);
			binExpr.check(currentSymbolTable, ctx);

			return binExpr;
		
		case 2: // Unary Expression of type `OP expr`
			ExprNode expr = (ExprNode) visit(ctx.expr(0));
			WACCUnOp unaryOp = WACCUnOp.evalUnOp(ctx.getChild(0).getText());
			UnExprNode unaryExpr  = new UnExprNode(unaryOp, expr);
			unaryExpr.check(currentSymbolTable, ctx);
			
			return expr;

		default: // in this case this is a single rule (i.e. int_liter, char_liter)
			return visit(ctx.getChild(0));
		}
	}

	@Override
	public WACCTree visitBlock_stat(Block_statContext ctx) {
		currentSymbolTable = new SymbolTable(currentSymbolTable);
		StatNode stat = (StatNode) visit(ctx.stat());
		currentSymbolTable.finaliseScope();
		currentSymbolTable = currentSymbolTable.getParent();
		return stat;
	}


	/**
	 * @return
	 * 		Returns true iff there were no semantic error in the compiler.
	 */
	public boolean terminate() {
		return WACCException.ERROR_LISTENER.finish();
	}

}
