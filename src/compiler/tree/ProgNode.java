package tree;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
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

}
