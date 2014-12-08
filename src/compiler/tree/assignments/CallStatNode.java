package tree.assignments;

import java.util.Iterator;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.type.WACCType;
import WACCExceptions.IllegalCallException;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndeclaredIdentifierException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.AddImmToken;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.MovRegToken;
import assembly.tokens.PopToken;
import assembly.tokens.PushToken;
import assembly.tokens.StorePreIndexToken;


public class CallStatNode extends Assignable {
	
	private String ident;
	private ArgListNode args;
	private WACCType retType;
	private FuncDecNode decNode;
	
	public CallStatNode(FuncDecNode func, ArgListNode args) {
		this.retType = func.returnType;
		this.ident = func.getFuncName();
		this.args = args;
		this.decNode = func; /* Not sure if needed */
	}
	
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!st.containsRecursive(ident)) {
			new UndeclaredIdentifierException("Function " + ident + " has not been delcared", ctx);
			return false;
		}
		ParamListNode params = decNode.getParams();
		if(!(args.size() == params.size())) {
			new IllegalCallException("The function call to " + ident + " has an incorrect number of arguments", ctx);
			return false;
		}
		if(!(args.compareToParamList(params))) {
			new IncompatibleTypesException("Arguments in call to " + ident + " have incorrect types.", ctx);
			return false;
		}
		return true;
	}
	
	public WACCType getType() {
		return retType;
	}
	
	@Override 
	public TokenSequence toAssembly(Register r) {
		TokenSequence seq = new TokenSequence();
		Iterator<ExprNode> argExprs = args.reverseIterator();
		int stackOffset = 0;
		while (argExprs.hasNext()) {
			ExprNode expr = argExprs.next();
			seq.appendAll(expr.toAssembly(r));
			seq.append(new StorePreIndexToken(Register.sp, r));
			stackOffset += 4;
		}
		seq.append(new PushToken(Register.R3));
		seq.append(new BranchLinkToken("f_" + ident));
		seq.append(new PopToken(Register.R3));
		seq.append(new AddImmToken(Register.sp, Register.sp, Integer.toString(stackOffset)));
		seq.append(new MovRegToken(r, Register.R0));
		return seq;
	}
}
