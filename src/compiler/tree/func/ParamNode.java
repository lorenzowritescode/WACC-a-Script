package tree.func;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.VarNode;
import tree.type.WACCType;
import assembly.StackPosition;

/* Represents a single parameter of a function
 * Contains information of the type and identifier * 
 */

public class ParamNode extends VarNode {

	public ParamNode(WACCType paramType, String ident) {
		super(paramType, ident);
	}

	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx)  {
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
	
	public void setPosition(StackPosition pos) {
		this.position = pos;
	}
	
}
