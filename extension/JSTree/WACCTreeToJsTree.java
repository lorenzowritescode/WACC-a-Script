package JSTree;

import java.util.ArrayList;

import tree.ProgNode;

import java.util.List;

import JSTree.assignable.JSArrayLiter;
import JSTree.expr.JSBinExpr;
import JSTree.expr.JSBool;
import JSTree.expr.JSChar;
import JSTree.expr.JSExpr;
import JSTree.expr.JSInt;
import JSTree.expr.JSString;
import JSTree.expr.JSVar;
import JSTree.func.JSFunc;
import JSTree.stat.JSAssignStat;
import JSTree.stat.JSExitStat;
import JSTree.stat.JSIfStat;
import JSTree.stat.JSPrint;
import JSTree.stat.JSReadStat;
import JSTree.stat.JSReturnStat;
import JSTree.stat.JSSeqStat;
import JSTree.stat.JSStat;
import JSTree.stat.JSVarDec;
import JSTree.stat.JSWhileStat;
import tree.ProgNode;
import tree.WACCTree;
import tree.assignments.*;
import tree.expr.*;
import tree.func.*;
import tree.stat.*;
import visitor.WACCTreeVisitor;

public class WACCTreeToJsTree extends WACCTreeVisitor<JSTree> {

	private WACCTree progTree;

	public WACCTreeToJsTree(WACCTree progTree) {
		this.progTree = progTree;
	}
	
	public static final JSTree EMPTY_NODE = new JSTree() {
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
	public JSAssignStat visitAssignStatNode(AssignStatNode node) {
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
		return new JSAssignStat(lhs, rhs);
	}

	@Override
	public JSTree visitBlockStatNode(BlockStatNode node) {
		// TODO Implement Block Stat Node
		return null;
	}

	@Override
	public JSExitStat visitExitStat(ExitStat node) {
		JSExpr exitVal = (JSExpr) visit(node.getExpr());
		return new JSExitStat(exitVal);
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
		JSTree lhs = visit(node.getLhs());
		JSTree rhs = visit(node.getRhs());
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
			functions.add(jsf);
		}
		
		JSStat body = (JSStat) visit(node.getProgBody());
		
		return new JSProg(functions, body);
	}
	
	
	

	@Override
	public JSFunc visitFuncDecNode(FuncDecNode node) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Implement visitUnExpr
		return null;
	}

	@Override
	public JSVar visitVarNode(VarNode node) {
		String ident = node.getIdent();
		return new JSVar(ident);
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
