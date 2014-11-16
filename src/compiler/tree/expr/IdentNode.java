package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import assignments.AssignLhsNode;
import symboltable.SymbolTable;
import tree.type.WACCType;

public class IdentNode extends ExprNode implements AssignLhsNode {
	
	private String ident;
	private WACCType type;
	
	public IdentNode(WACCType type, String ident) {
		this.ident = ident;
		this.type = type;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		return true;
	}
	
	@Override
	public boolean checkPreDef( SymbolTable st, String identName) {
		if (!st.containsRecursive(identName)) {
			return false;
		}
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
