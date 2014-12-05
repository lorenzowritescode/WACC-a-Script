package tree.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.IntOverflowException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.LoadToken;

/* Represents the value of an Integer
 * Constructed with a String (e.g "42") 
 * Rule: int-sign? digit+
 */

public class IntLeaf extends ExprNode {

	private String value;
	
	public IntLeaf(String val) {
		this.value = val;
	}

	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		long integer = Long.valueOf(value);
		if (integer < - (Math.pow(2, 31)) || integer > (Math.pow(2, 31) + 1)) {
			throw new IntOverflowException("The absolute value, " + value + " is too large", ctx);
		}
		return true;
	}

	@Override
	public String toString() {
		return value;
	}
	
	@Override
	public WACCType getType() {
		return WACCType.INT;
	}
	
	@Override
	public TokenSequence toAssembly(Register r) {
		return new TokenSequence( new LoadToken(r, value) );
	}

	@Override
	public int weight() {
		return 1;
	}
	

}
