package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndeclaredIdentifierException;
import assignments.AssignLhsNode;
import symboltable.SymbolTable;
import tree.func.FuncDecNode;
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
		if (!st.containsRecursive(ident)) {
			throw new UndeclaredIdentifierException(ident + " hasn't been defined", ctx);
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
