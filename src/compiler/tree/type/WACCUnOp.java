package tree.type;

import tree.expr.ExprNode;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.EorToken;

public abstract class WACCUnOp {
	
	public abstract boolean check(ExprNode e);
	public abstract WACCType getType();
	public abstract TokenSequence apply(Register register);
	
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

		@Override
		public String toString() {
			return "NOT";
		}

		@Override
		public TokenSequence apply(Register register) {
			return new TokenSequence(
				new EorToken(register, register, "#1")
			);
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
		
		@Override
		public String toString() {
			return "NEG";
		}

		@Override
		public TokenSequence apply(final Register register) {
			return new TokenSequence(
					new InstrToken() {
						@Override
						public String toString() {
							return "RSB " + register.toString() + ", " + register.toString() + ", #0";
						}
					}
					// todo: this should also throw an integer overflow error
			);
		}
	};
	
	public static final WACCUnOp LEN = new WACCUnOp() {
		
		@Override
		public WACCType getType() {
			return WACCType.INT;
		}
		
		@Override
		public boolean check(ExprNode e) {
			return e.getType() instanceof ArrayType;
		}
		
		@Override
		public String toString() {
			return "LEN";
		}

		@Override
		public TokenSequence apply(Register register) {
			// TODO Auto-generated method stub
			return null;
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
		
		@Override
		public String toString() {
			return "ORD";
		}

		@Override
		public TokenSequence apply(Register register) {
			// TODO Auto-generated method stub
			return null;
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
		
		@Override
		public String toString() {
			return "CHR";
		}

		@Override
		public TokenSequence apply(Register register) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}
