package tree;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.stat.StatNode;
import tree.type.WACCType;
import assembly.InstrToken;
import assembly.Register;
import assembly.StackAllocator;
import assembly.TokenSequence;
import assembly.tokens.LabelToken;
import assembly.tokens.LoadToken;
import assembly.tokens.PopToken;
import assembly.tokens.PushToken;

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
		functionDeclarations.append(
				new InstrToken() {
							@Override
							public String toString() { return ".text\n\n.global main"; }
						}
				);
		
		for (FuncDecNode f:functions) {
			functionDeclarations.appendAll(f.toAssembly(register));
		}
		
		TokenSequence progSeq = progBody.toAssembly(register);
		
		//Create main label and push lr
		progSeq.prependAll( 
				new TokenSequence(
						new LabelToken("main"),
						new PushToken(Register.lr),
						StackAllocator.stackAllocator.getInitialisation()
				)
			);
		
		// after the progSeq has been visited, we retrieve the eventual Stack Allocations
		progSeq.appendAll(
				new TokenSequence(
						StackAllocator.stackAllocator.getTermination(),
						new LoadToken(Register.R0, "0"),
						new PopToken(Register.pc),
						new InstrToken() {
							@Override
							public String toString() { return ".ltorg"; }
						}
				)
			);
		
		return functionDeclarations.appendAll(progSeq);
	}
}
