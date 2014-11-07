package antlr;

import WACCExceptions.InvalidTypeException;
import antlr.WACCParser.TypeContext;

public enum WACCType {
	INT, BOOL, CHAR, STRING;
	
	public static WACCType evalType(TypeContext ctx){
		String type = ctx.getText();
		switch (type) {
		case "INT":
			return INT;
		case "BOOL":
			return BOOL;
		case "CHAR":
			return CHAR;
		case "STRING":
			return STRING;
		default:
			throw new InvalidTypeException("The type provided was not recognised.", ctx);
		}
	}
}
