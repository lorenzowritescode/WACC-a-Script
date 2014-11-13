package assignments;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndefinedFunctionException;


public class CallStatNode extends Assignable {
	
	private String ident;
	private ArgListNode args;
	private WACCType retType;
	private FuncDecNode decNode;
	
	public CallStatNode(FuncDecNode func, ArgListNode args) {
		//TODO: Sort this out maybe... passing FuncDecNode here seems a bit hackey. 
		this.retType = func.returnType;
		this.ident = func.getFuncName();
		this.args = args;
	}
	
	public boolean check( SymbolTable st, RuleContext ctx ) {
		if (!st.containsRecursive(ident)) {
			el.record(new UndefinedFunctionException(
					"Function " + ident + " has not been delcared", ctx));
			return false;
		}
		
		if(!(args.compareToParamList(decNode.getParams()))) {
			el.record(new IncompatibleTypesException(
					"Arguments in call to " + ident + "Have incorrect Types", ctx));
			return false;
		}
		return true;
	}
	
	public WACCType getType() {
		return retType;
	}
}
