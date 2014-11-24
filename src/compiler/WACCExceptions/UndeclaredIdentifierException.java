package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

/*
 * Used when an identifier is used which is not defined in symbol table
 */

@SuppressWarnings("serial")
public class UndeclaredIdentifierException extends WACCException {

	public UndeclaredIdentifierException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
