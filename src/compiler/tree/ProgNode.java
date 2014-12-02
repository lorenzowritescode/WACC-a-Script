package tree;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import symboltable.SymbolTable;
import tree.expr.VarNode;
import tree.func.FuncDecNode;
import tree.stat.StatNode;
import tree.type.WACCType;

public class ProgNode extends WACCTree {
	
	private List<FuncDecNode> functions;
	private StatNode progBody;

	public ProgNode(List<FuncDecNode> functions, StatNode progBody) {
		this.functions = functions;
		this.progBody = progBody;
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		return true;
	}

	@Override
	public WACCType getType() {
		throw new UnsupportedOperationException("getType() is not supported in ProgNode");
	}

	@Override
	public TokenSequence toAssembly(Register register) {
		// functions are stored at the top of the program
		TokenSequence functionDeclarations = new TokenSequence();
		for (FuncDecNode f:functions) {
			functionDeclarations.appendAll(f.toAssembly(register));
		}
		
		TokenSequence progSeq = progBody.toAssembly(register);
		
		// after the progSeq has been visited, we retrieve the eventual Stack Allocations
		progSeq.prepend(VarNode.stackAllocator.getInitialisation());
		progSeq.append(VarNode.stackAllocator.getTermination());
		
		// we wrap the main program body with the appropriate instructions
		wrapMainBody(progSeq);
		
		return functionDeclarations.appendAll(progSeq);
	}
	
	private static void wrapMainBody(TokenSequence body) {
		InstrToken mainHeader = new InstrToken() {
			@Override
			public String toString() {
				return ".text\n\n"
						+ ".global main\n"
						+ "main:\n"
						+ "PUSH {lr}";
			}
		};
		
		InstrToken mainFooter = new InstrToken() {
			@Override
			public String toString() {
				return "LDR r0, =0\n"
						+ "POP {pc}\n"
						+ ".ltorg";
			}
		};
		
		body.prepend(mainHeader);
		body.append(mainFooter);
	}
}
