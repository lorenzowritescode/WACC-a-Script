package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.internal.matchers.StacktracePrintingMatcher;

import assembly.StackPosition;
import symboltable.SymbolTable;
import tree.assignments.AssignLhsNode;
import tree.type.WACCType;
import WACCExceptions.UndeclaredIdentifierException;

/* Represents an Identifier and its declared type
 * Constructed with and type and string (e.g BOOL, "myBool")
 * Rule: (' '|'a'-'z'|'A'-'Z')(' '|'a'-'z'|'A'-'Z'|'0'-'9')*
 */

public class VarNode extends ExprNode implements AssignLhsNode {
	
	private String ident;
	private WACCType type;
	private StackPosition position;
	
	public VarNode(WACCType type, String ident) {
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

	@Override
	public int weight() {
		return 1;
	}

	public void setPos(StackPosition pos) {
		this.position = pos;
	}

}
