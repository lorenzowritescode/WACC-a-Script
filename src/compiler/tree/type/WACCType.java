package tree.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static final WACCType NULL = new WACCType() {
		
		@Override
		public String toString() {
			return "WACC-null";
		}
		
		@Override
		public boolean isCompatible(WACCType other) {
			return true;
		}
	};
	
	public static final String pairRegexSplitter = " *[\\)\\(,] *";
	public static final String arrayRegexSplitter = "[\\[\\]]";
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
		case "pair":
			return new PairType(null, null);
		default:
			//matches any array
			Pattern arrayPattern = Pattern.compile("\\[\\]");
			Matcher arrayMatcher = arrayPattern.matcher(typeString);
			if (arrayMatcher.find()) {
				WACCType baseType = evalType(typeString.split(arrayRegexSplitter)[0]);
				return new ArrayType(baseType);
			}
			//matches any pair
			if (typeString.contains("pair")) {
				// Extract inner types
				String[] innerTypes = typeString.split(pairRegexSplitter);
				String fstString = innerTypes[1];
				String sndString = innerTypes[2];
				
				// Pairs of pairs have type `pair(null, null)` 
				WACCType fstType = fstString.equals("pair") ? WACCType.NULL : evalType(fstString);
				WACCType sndType = sndString.equals("pair") ? WACCType.NULL : evalType(sndString);
				
				
				return new PairType(fstType, sndType); 
			}
			
			throw new InvalidTypeException("The type provided was not recognised.");
		}
	}
	
}