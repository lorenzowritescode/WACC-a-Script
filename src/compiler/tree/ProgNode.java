package tree;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.stat.StatNode;
import tree.type.WACCType;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LabelToken;
import assembly.tokens.MovImmToken;
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
	public TokenSequence toAssembly(Register r) {
		TokenSequence seq = new TokenSequence();
		
		//Create main label and push lr
		seq.append(new LabelToken("main"));
		seq.append(new PushToken(Register.lr));
		
		//Get assembly of main function
		seq.appendAll(progBody.toAssembly(r));
		
		//Get assembly of functions
		for(FuncDecNode func : functions) {
			seq.appendAll(func.toAssembly(r));
		}
		
		//return 0 and Pop pc
		seq.append(new MovImmToken(Register.R0, "#0"));
		seq.append(new PopToken(Register.pc));
		
		
		return seq;
	}

}
