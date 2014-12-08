package tree.func;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.stat.StatNode;
import tree.type.WACCType;
import assembly.InstrToken;
import assembly.Register;
import assembly.StackAllocator;
import assembly.TokenSequence;
import assembly.tokens.EasyToken;
import assembly.tokens.LabelToken;
import assembly.tokens.PopToken;
import assembly.tokens.PushToken;

/* Represents a Function declaration
 * Contains information of the function name, return type, function parameters, 
 * function body and a Bool that becomes true once the function body has been added.
 */

public class FuncDecNode extends WACCTree {

	public final WACCType returnType;
	private String funcName;
	private ParamListNode params;
	private StatNode funcBody;
	private boolean complete;

	public FuncDecNode(WACCType returnType, String funcName, ParamListNode params) {
		this(returnType, funcName);
		this.params = params;
		complete = false;
	}

	public FuncDecNode(WACCType returnType, String funcName) {
		this.returnType = returnType;
		this.funcName = funcName;
		complete = false;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return complete;
	}

	@Override
	public WACCType getType() {
		return this.returnType;
	}
	
	public String getFuncName() {
		return funcName;
	}

	public ParamListNode getParams() {
		return params;
	}

	public void addFuncBody(StatNode funcBody) {
		this.funcBody = funcBody;
		complete = true;
	}
	
	@Override
	public TokenSequence toAssembly(Register r) {
		InstrToken label = new LabelToken("f_" + funcName);
		InstrToken push = new PushToken(Register.lr);
		TokenSequence startSequence = new TokenSequence(label, push);
		
		StackAllocator.stackAllocator.enterNewScope();
		params.allocateParamsOnStack();
		
		TokenSequence stackAllocSequence = StackAllocator.stackAllocator.getInitialisation();
		TokenSequence body = funcBody.toAssembly(r);
		TokenSequence stackTerminationSequence = StackAllocator.stackAllocator.getTermination();
		TokenSequence middleSequence = 
				new TokenSequence(
						stackAllocSequence, 
						body, 
						stackTerminationSequence);
		
		StackAllocator.stackAllocator.exitScope();
		InstrToken pop = new PopToken(Register.pc);
		InstrToken ltorg = new EasyToken(".ltorg");
		TokenSequence finalSequence = new TokenSequence(pop, ltorg);
		
		return startSequence
				.appendAll(middleSequence)
				.appendAll(finalSequence);
	}

}
