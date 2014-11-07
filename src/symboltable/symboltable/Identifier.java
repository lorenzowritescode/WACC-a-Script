package symboltable;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.TypeContext;
import antlr.WACCType;

public abstract class Identifier {
	public RuleContext ctx;
	protected WACCType identType;
	
	public Identifier(RuleContext ctx) {
		this.ctx = ctx;
	}
	
	public WACCType getType() {
		return identType;
	}
}
