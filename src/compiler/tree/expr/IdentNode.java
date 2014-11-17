package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.UndeclaredIdentifierException;
import assignments.AssignLhsNode;
import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.type.WACCType;

/* Represents an Identifier and its declared type
 * Constructed with and type and string (e.g BOOL, "myBool")
 * Rule: (' '|'a'-'z'|'A'-'Z')(' '|'a'-'z'|'A'-'Z'|'0'-'9')*
 */

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
