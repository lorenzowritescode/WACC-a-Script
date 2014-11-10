package symboltable;

import org.antlr.v4.runtime.RuleContext;

import antlr.WACCParser.Variable_declarationContext;
import antlr.WACCType;

public class VariableIdentifier extends Identifier {

	public VariableIdentifier(RuleContext ctx) {
		super(ctx);
		// Extracting type from node
		Variable_declarationContext vctx = (Variable_declarationContext) ctx;
		identType = WACCType.evalType(vctx.type());
	}

}
