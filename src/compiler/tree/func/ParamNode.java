package tree.func;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.type.WACCType;

/* Represents a single parameter of a function
 * Contains information of the type and identifier * 
 */

public class ParamNode extends WACCTree {
	
	private WACCType type;
	private String ident;
	
	public ParamNode(WACCType paramType, String ident) {
		this.type = paramType;
		this.ident = ident;
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
	

}
