package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("serial")
public class UndeclaredIdentifierException extends WACCException {

/*
 * Used when an identifier is used which is not defined in symbol table
 */

	public UndeclaredIdentifierException(String exceptionMessage, ParserRuleContext ctx) {
		super(exceptionMessage, ctx);
	}

}
