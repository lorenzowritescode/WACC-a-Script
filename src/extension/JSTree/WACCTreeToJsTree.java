package JSTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import JSTree.JSProg;
import JSTree.JSTree;
import tree.ProgNode;
import tree.WACCTree;
import tree.assignments.ArgListNode;
import tree.assignments.ArrayElemNode;
import tree.assignments.ArrayLiterNode;
import tree.assignments.CallStatNode;
import tree.assignments.NewPairNode;
import tree.assignments.PairElemNode;
import tree.assignments.PairElemNode.ORDER;
import tree.expr.BinExprNode;
import tree.expr.BoolLeaf;
import tree.expr.CharLeaf;
import tree.expr.ExprNode;
import tree.expr.IntLeaf;
import tree.expr.PairLeaf;
import tree.expr.PairLiterNode;
import tree.expr.StringLeaf;
import tree.expr.UnExprNode;
import tree.expr.VarNode;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.stat.AssignStatNode;
import tree.stat.BlockStatNode;
import tree.stat.ExitStat;
import tree.stat.FreeStat;
import tree.stat.IfStatNode;
import tree.stat.PrintLnStat;
import tree.stat.PrintStat;
import tree.stat.ReadStatNode;
import tree.stat.ReturnStatNode;
import tree.stat.SeqStatNode;
import tree.stat.SkipStatNode;
import tree.stat.VarDecNode;
import tree.stat.WhileStatNode;
import tree.type.WACCUnOp;
import visitor.WACCTreeVisitor;
import JSTree.assignable.JSArrayElem;
import JSTree.assignable.JSArrayLiter;
import JSTree.expr.JSBinExpr;
import JSTree.expr.JSBool;
import JSTree.expr.JSChar;
import JSTree.expr.JSExpr;
import JSTree.expr.JSInt;
import JSTree.expr.JSNewPair;
import JSTree.expr.JSNull;
import JSTree.expr.JSPair;
import JSTree.expr.JSPairElem;
import JSTree.expr.JSString;
import JSTree.expr.JSVar;
import JSTree.func.JSArgList;
import JSTree.func.JSFunc;
import JSTree.func.JSFuncCall;
import JSTree.func.JSLibFuncCall;
import JSTree.func.JSParam;
import JSTree.func.JSParamList;
import JSTree.stat.JSAssignStat;
import JSTree.stat.JSBlockStat;
import JSTree.stat.JSExitStat;
import JSTree.stat.JSIfStat;
import JSTree.stat.JSPrint;
import JSTree.stat.JSReadStat;
import JSTree.stat.JSReturnStat;
import JSTree.stat.JSSeqStat;
import JSTree.stat.JSStat;
import JSTree.stat.JSVarDec;
import JSTree.stat.JSWhileStat;

public class WACCTreeToJsTree extends WACCTreeVisitor<JSTree> {

	private WACCTree progTree;
	private HashMap<String, String> funcDeps;
	private HashMap<String, JSFunc> symboltable;
	
	public WACCTreeToJsTree(WACCTree progTree) {
		this.progTree = progTree;
		this.symboltable = new HashMap<>();
	}
	
	public WACCTreeToJsTree(WACCTree progTree, HashMap<String, String> funcDeps) {
		this(progTree);
		this.funcDeps = funcDeps;
	}

	public static final JSTree EMPTY_NODE = new JSStat() {
		@Override
		public String toCode() {
			return "";
		}
	};
	
	@Override
	public JSTree visit(WACCTree node) {
		return node.accept(this);
	}

	public String init() {
		JSTree finalTree = progTree.accept(this);
		return finalTree.toCode();
	}

	@Override
	public JSTree visitAssignStatNode(AssignStatNode node) {
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
		if( lhs instanceof JSArrayElem 
			&& ((JSArrayElem) lhs).getType().equals("char") ) {
			String var = ((ArrayElemNode) node.getLhs()).getVar().getIdent();
			int index = ((JSInt) ((JSArrayElem) lhs).getLocations().get(0)).getVal();
			String c = ((JSChar) rhs).getText();
			return new JSChangeString(var, index, c);
		}
		return new JSAssignStat(lhs, rhs);
	}

	@Override
	public JSTree visitBlockStatNode(BlockStatNode node) {
		JSStat stat = (JSStat) visit(node.getStat());
		return new JSBlockStat(stat);
	}

	@Override
	public JSExitStat visitExitStat(ExitStat node) {
		return new JSExitStat();
	}

	@Override
	public JSTree visitFreeStat(FreeStat node) {
		return EMPTY_NODE;
	}

	@Override
	public JSPrint visitPrintLnStat(PrintLnStat node) {
		JSExpr expr = (JSExpr) visit(node.getExpr());
		return new JSPrint(expr);
	}

	@Override
	public JSPrint visitPrintStat(PrintStat node) {
		JSExpr expr = (JSExpr) visit(node.getExpr());
		return new JSPrint(expr);
	}

	@Override
	public JSReadStat visitReadStatNode(ReadStatNode node) {
		JSTree lhs = visit(node.getLhs());
		return new JSReadStat(lhs);
	}

	@Override
	public JSReturnStat visitReturnStatNode(ReturnStatNode node) {
		JSTree expr = visit(node.getExpr());
		return new JSReturnStat(expr);
	}

	@Override
	public JSSeqStat visitSeqStatNode(SeqStatNode node) {
		JSStat lhs = (JSStat) visit(node.getLhs());
		JSStat rhs = (JSStat) visit(node.getRhs());
		return new JSSeqStat(lhs, rhs);
	}

	@Override
	public JSTree visitSkipStatNode(SkipStatNode node) {
		return EMPTY_NODE;
	}

	@Override
	public JSVarDec visitVarDecNode(VarDecNode node) {
		JSExpr var = (JSExpr) visit(node.getVar());
		JSTree rhs = visit(node.getRhsTree());
		return new JSVarDec(var, rhs);
	}

	@Override
	public JSIfStat visitIfStatNode(IfStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getIfCond());
		JSStat thenStat = (JSStat) visit(node.getThenStat());
		JSStat elseStat = (JSStat) visit(node.getElseStat());
		return new JSIfStat(condition, thenStat, elseStat);
	}

	@Override
	public JSWhileStat visitWhileStatNode(WhileStatNode node) {
		JSExpr condition = (JSExpr) visit(node.getLoopCond());
		JSStat loopBody = (JSStat) visit(node.getLoopBody());
		return new JSWhileStat(condition, loopBody);
	}
	


	@Override
	public JSTree visitProgNode(ProgNode node) {
		List<JSFunc> functions = new ArrayList<>();
		for(FuncDecNode f:node.getFunctions()) {
			JSFunc jsf = visitFuncDecNode(f);
			symboltable.put(f.getFuncName(), jsf);
			functions.add(jsf);
		}
		
		JSStat body = (JSStat) visit(node.getProgBody());
		
		return new JSProg(functions, body, funcDeps);
	}
	
	
	

	@Override
	public JSFunc visitFuncDecNode(FuncDecNode node) {
		JSParamList params = visitParamListNode(node.getParams());
		String funcName = node.getFuncName();
		JSStat body = (JSStat) visit(node.getFuncBody());
		
		return new JSFunc(funcName, params, body);
	}

	@Override
	public JSParamList visitParamListNode(ParamListNode node) {
		List<JSParam> params = new ArrayList<>();
		for (ParamNode n:node) {
			JSParam p = visitParamNode(n);
			params.add(p);
		}
		
		return new JSParamList(params);
	}

	@Override
	public JSParam visitParamNode(ParamNode node) {
		String ident = node.getIdent();
		return new JSParam(ident);
	}

	@Override
	public JSTree visitCallStatNode(CallStatNode node) {
		JSArgList args = visitArgListNode(node.getArgs());
		String functionName = node.getIdent();
		
		// This block strips down the dependency name from the path
		if (funcDeps.containsKey(functionName)
				&& !symboltable.containsKey(functionName)) {
			String filePath = funcDeps.get(functionName);
			String dep = new File(filePath).getName();
			dep = dep.substring(0, dep.lastIndexOf('.'));
			return new JSLibFuncCall(dep, functionName, args);
		} else if ( symboltable.containsKey(functionName)) {
			return new JSFuncCall(symboltable.get(functionName), args);
		}
		
		return new JSFuncCall(functionName, args, symboltable);
	}

	@Override
	public JSBinExpr visitBinExprNode(BinExprNode node) {
		JSExpr lhs = (JSExpr) visit(node.getLhs());
		String op = node.getOperator().toString();
		JSExpr rhs = (JSExpr) visit(node.getRhs());
		return new JSBinExpr(lhs, op, rhs);
				
	}

	@Override
	public JSChar visitCharLeaf(CharLeaf node) {
		String c = node.getText();
		return new JSChar(c);
	}

	@Override
	public JSBool visitBoolLeaf(BoolLeaf node) {
		boolean b = node.getValue();
		return new JSBool(b);
	}

	@Override
	public JSInt visitIntLeaf(IntLeaf node) {
		int i = Integer.parseInt(node.getValue());
		return new JSInt(i);
	}

	@Override
	public JSString visitStringLeaf(StringLeaf node) {
		String s = node.toString();
		return new JSString(s);
	}

	@Override
	public JSTree visitUnExprNode(UnExprNode node) {
		JSExpr expr = (JSExpr) visit(node.getExpr());
		WACCUnOp op = node.getOperator();
		return op.applyJS(expr);
	}

	@Override
	public JSVar visitVarNode(VarNode node) {
		String ident = node.getIdent();
		return new JSVar(ident);
	}
	
	@Override
	public JSArgList visitArgListNode(ArgListNode node) {
		List<JSExpr> args = new ArrayList<>();
		
		for (ExprNode n:node) {
			JSExpr e = (JSExpr) visit(n);
			args.add(e);
		}
		
		return new JSArgList(args);
	}

	@Override
	public JSTree visitPairLeaf(PairLeaf node) {
		String ident = node.getIdent();
		return new JSPair(ident);
	}

	@Override
	public JSNull visitPairLiterNode(PairLiterNode node) {
		// PairLiter nodes are always null
		return new JSNull();
	}


	@Override
	public JSNewPair visitNewPairNode(NewPairNode node) {
		JSExpr fst = (JSExpr) visit(node.getFst());
		JSExpr snd = (JSExpr) visit(node.getSnd());
		return new JSNewPair(fst, snd);
	}

	@Override
	public JSPairElem visitPairElemNode(PairElemNode node) {
		JSExpr expr = (JSExpr) visit(node.getExpr());
		ORDER order = node.getOrder();
		return new JSPairElem(expr, order);
	}

	@Override
	public JSTree visitArrayElemNode(ArrayElemNode node) {
		JSVar var = (JSVar) visit(node.getVar());
		ArrayList<ExprNode> locations = node.getLocations();
		ArrayList<JSExpr> jsLocations = new ArrayList<JSExpr>();
		for(ExprNode en : locations) {
			jsLocations.add((JSExpr) visit(en));
		}
		JSArrayElem arrayElem = new JSArrayElem(jsLocations, var, node.getType().toString());
		return arrayElem;
	}
	
	@Override
	public JSTree visitArrayLiterNode(ArrayLiterNode node) {
		ArrayList<JSExpr> elems = new ArrayList<JSExpr>();
		ArrayList<ExprNode> elemNodes = node.getElems();
		for (ExprNode expr : elemNodes) {
			elems.add((JSExpr) visit(expr));
		}
		return new JSArrayLiter(elems);
	}
	
}
