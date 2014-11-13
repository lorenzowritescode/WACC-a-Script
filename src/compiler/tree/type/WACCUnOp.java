package tree.type;

import tree.expr.ExprNode;

public abstract class WACCUnOp {
	public abstract boolean check(ExprNode e);
	public abstract WACCType getType();
	
	/*
	 * Utility method to parse a unary operator.
	 */
	public static WACCUnOp evalUnOp(String opString) {
		switch (opString) {
		case "!":
			return NOT;
		case "-":
			return NEG;
		case "len":
			return LEN;
		case "ord":
			return ORD;
		case "chr":
			return CHR;
			
		default:
			throw new IllegalArgumentException("The String provided " + opString + " did not match any unary operators.");
		}
	}
	
	/*
	 * Static final instances of the WACC Unary Operators:
	 * NOT, NEG, LEN, ORD, CHR
	 */
	public static final WACCUnOp NOT = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.BOOL;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() == WACCType.BOOL;
		}
	};
	
	public static final WACCUnOp NEG = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.INT;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() == WACCType.INT;
		}
	};
	
	public static final WACCUnOp LEN = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.INT;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() instanceof ArrayTypeNode;
		}
	};
	
	public static final WACCUnOp ORD = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.INT;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() == WACCType.CHAR;
		}
	};
	
	public static final WACCUnOp CHR = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.CHAR;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() == WACCType.INT;
		}
	};
}
