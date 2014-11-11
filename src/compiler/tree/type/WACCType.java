package tree.type;

import WACCExceptions.InvalidTypeException;
import antlr.WACCParser.TypeContext;


public abstract class WACCType {

	public abstract boolean isCompatible(WACCType other);
	
	// Static instances of base types
	public static final WACCType BOOL = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == BOOL;
		}
	};
	public static final WACCType INT = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == INT;
		}
	};
	public static final WACCType STRING = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == STRING;
		}
	};
	public static final WACCType CHAR = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == CHAR;
		}
	};
	
	// Utility method for converting a WACCParser.TypeContext into a WACCType
	public static WACCType evalType(TypeContext ctx) {
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