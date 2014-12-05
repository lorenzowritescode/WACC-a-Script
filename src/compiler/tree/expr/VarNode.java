package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.assignments.AssignLhsNode;
import tree.type.WACCType;
import WACCExceptions.UndeclaredIdentifierException;
import assembly.InstrToken;
import assembly.Register;
import assembly.StackPosition;
import assembly.TokenSequence;
import assembly.tokens.PrintBoolToken;
import assembly.tokens.PrintCharToken;
import assembly.tokens.PrintIntToken;
import assembly.tokens.PrintStringToken;

import com.sun.org.apache.bcel.internal.generic.ArrayType;

/* Represents an Identifier and its declared type
 * Constructed with and type and string (e.g BOOL, "myBool")
 * Rule: (' '|'a'-'z'|'A'-'Z')(' '|'a'-'z'|'A'-'Z'|'0'-'9')*
 */

public class VarNode extends ExprNode implements AssignLhsNode {
	
	protected String ident;
	protected WACCType type;
	protected StackPosition position;
	
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
	
	@Override 
	public TokenSequence toAssembly(Register r) {
		return position.toAssembly(r);
	}
	
	public StackPosition getPosition() {
		return position;
	}

	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		TokenSequence storeInVariable = position.toStoreAssembly(dest);
		return storeInVariable;
	}
	
	

}
