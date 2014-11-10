package antlr;

import WACCExceptions.InvalidTypeException;
import antlr.WACCParser.TypeContext;

public enum WACCType {
	INT, BOOL, CHAR, STRING;
	
	public static WACCType evalType(TypeContext ctx){
		String type = ctx.getText();
		switch (type) {
		case "int":
			return INT;
		case "bool":
			return BOOL;
		case "char":
			return CHAR;
		case "string":
			return STRING;
		default:
			throw new InvalidTypeException("The type provided was not recognised.", ctx);
		}
	}
}
