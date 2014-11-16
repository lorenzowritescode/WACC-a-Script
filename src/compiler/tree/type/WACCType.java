package tree.type;

import WACCExceptions.InvalidTypeException;
import antlr.WACCParser.TypeContext;


public abstract class WACCType {

	public abstract boolean isCompatible(WACCType other);
	public abstract String toString();
	
	/*
	 * The following are here as they are definite types,
	 * with no fields or parameters. Any reference to basic
	 * types will be references to these objects.
	 */
	public static final WACCType BOOL = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == BOOL;
		}
		
		@Override
		public String toString() {
			return "bool";
		}
	};
	public static final WACCType INT = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == INT;
		}
		
		@Override
		public String toString() {
			return "int";
		}
	};
	public static final WACCType STRING = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == STRING;
		}
		
		@Override
		public String toString() {
			return "string";
		}
	};
	public static final WACCType CHAR = new WACCType() {
		
		@Override
		public boolean isCompatible(WACCType other) {
			return other == CHAR;
		}
		
		@Override
		public String toString() {
			return "char";
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