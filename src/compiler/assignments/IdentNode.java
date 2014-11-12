package assignments;

import org.antlr.v4.runtime.RuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public class IdentNode extends AssignLhsNode {
	
	private String ident;
	private WACCType type;
	
	public IdentNode(WACCType type, String ident) {
		this.ident = ident;
		this.type = type;
	}
	
	@Override
	public boolean check( SymbolTable st, RuleContext ctx ) {
		return true;
	}

	@Override
	public WACCType getType() {
		return type;
	}
	
	public String getIdent() {
		return ident;
	}
	
	
}
