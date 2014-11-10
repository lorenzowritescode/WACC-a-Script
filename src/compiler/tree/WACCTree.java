package tree;

import org.antlr.v4.runtime.RuleContext;

public abstract class WACCTree {
	private RuleContext ctx;

	public WACCTree(RuleContext ctx) {
		this.ctx = ctx;
	}
	
	public abstract boolean check();
}
