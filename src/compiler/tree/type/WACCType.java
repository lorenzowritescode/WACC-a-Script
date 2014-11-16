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
	
	public static WACCType evalType(TypeContext ctx) {
		return evalType(ctx.getText());
	}
	
	// Utility method for converting a WACCParser.TypeContext into a WACCType
	public static WACCType evalType(String typeString) {
		switch (typeString) {
		case "int":
			return INT;
		case "bool":
			return BOOL;
		case "char":
			return CHAR;
		case "string":
			return STRING;
		default:
			//matches any array
			if (typeString.matches("*[]")) {
				WACCType baseType = evalType(typeString.split("[")[0]);
				return new ArrayType(baseType);
			} 
			//matches any pair
			if (typeString.matches("pair(*")) { 
				WACCType fstType = evalType(typeString.split("(|,|)")[1]);
				WACCType sndType = evalType(typeString.split("(|,|)")[2]);
				return new PairType(fstType, sndType); 
			}
			
			throw new InvalidTypeException("The type provided was not recognised.");
		}
	}
	
}