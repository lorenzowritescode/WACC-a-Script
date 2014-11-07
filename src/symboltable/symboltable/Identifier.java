package symboltable;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.TypeContext;

public abstract class Identifier {
	public RuleContext ctx;
	
	public Identifier(RuleContext ctx) {
		this.ctx = ctx;
	}
	
	public abstract TypeContext getType();
}
