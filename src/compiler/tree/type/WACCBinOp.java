package tree.type;

import tree.expr.ExprNode;

public abstract class WACCBinOp {
	private WACCType returnType;

	public WACCBinOp(WACCType returnType) {
		this.returnType = returnType;
	}

	public abstract boolean check(ExprNode lhs, ExprNode rhs);

	public WACCType getType() {
		return this.returnType;
	};

	/*
	 * There are only 3 types of logic to check that `rhs BinOp lhs` is correct:
	 * 1 - lhs and rhs must have same type as returnType (Arithmetic BinOps, && and ||)
	 * 2 - lhs and rhs must be of equal type, which must be either INT or CHAR (>=, >, <, <=)
	 * 3 - always correct (== and !=)
	 */

	private boolean mustEqualReturn(ExprNode lhs, ExprNode rhs) {
		return lhs.getType() == rhs.getType() && lhs.getType() == this.returnType;
	};
	private boolean typesMustMatch(ExprNode lhs, ExprNode rhs) {
		if (lhs.getType() == WACCType.INT)
			return rhs.getType() == WACCType.INT;
		else if (lhs.getType() == WACCType.CHAR)
			return rhs.getType() == WACCType.CHAR;
		else
			return false;
	}
	private boolean alwaysTrue(ExprNode lhs, ExprNode rhs) {
		return true;
	}

	/*
	 *  Arithmetic Operators return their input type.
	 */
	public final WACCBinOp MUL =  new WACCBinOp(WACCType.INT) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	public final WACCBinOp DIV =  new WACCBinOp(WACCType.INT) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	public final WACCBinOp MOD =  new WACCBinOp(WACCType.INT) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	public final WACCBinOp ADD =  new WACCBinOp(WACCType.INT) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	public final WACCBinOp SUB =  new WACCBinOp(WACCType.INT) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	/*
	 *  Boolean operators return BOOL, but input may be either INT or CHAR
	 */
	public final WACCBinOp GRT =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return typesMustMatch(lhs, rhs);
		}
	};
	public final WACCBinOp GRT_EQ =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return typesMustMatch(lhs, rhs);
		}
	};
	public final WACCBinOp LESS =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return typesMustMatch(lhs, rhs);
		}
	};
	public final WACCBinOp LESS_EQ =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return typesMustMatch(lhs, rhs);
		}
	};
	public final WACCBinOp DOUBLE_EQUAL =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return alwaysTrue(lhs, rhs);
		}
	};
	public final WACCBinOp NOT_EQUAL =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return alwaysTrue(lhs, rhs);
		}
	};
	/*
	 * Binary Boolean Operators return BOOL and take BOOl
	 */
	public final WACCBinOp AND =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
	public final WACCBinOp OR =  new WACCBinOp(WACCType.BOOL) {

		@Override
		public boolean check(ExprNode lhs, ExprNode rhs) {
			return mustEqualReturn(lhs, rhs);
		}
	};
}
