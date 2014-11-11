package tree.func;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.ParamContext;
import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

public class ParamNode extends WACCTree {
	private WACCType type;
	private String ident;
	private final ParamContext ctx;
	
	public ParamNode(ParamContext ctx) {
		this.ctx = ctx;
		this.type = WACCType.evalType(ctx.type());
		this.ident = ctx.ident().getText();
	}

	@Override
	public boolean check(SymbolTable st, RuleContext ctx)  {
			return true;
	}

	@Override
	public WACCType getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ParamNode) {
			ParamNode pn = (ParamNode) other;
			if (pn.getType() == type) {
				return true;
			}
		}
		return false;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public ParamContext getContext() {
		return ctx;
	}

}
