package tree.type;

import tree.expr.ExprNode;

/*
 * This class is used to represent the behaviour of WACC Binary Operators.
 * Their role is to check wether two provided expression (`lhs`, `rhs`) can be used in a binary expression with a given operator;
 * They are also used to infer the type of any given binary expression.
 */
public abstract class WACCBinOp {
	
	public WACCBinOp() {}

	public abstract boolean check(ExprNode lhs, ExprNode rhs);

	public abstract WACCType getType();

	/*
	 * There are only 4 types of logic to check that `rhs BinOp lhs` is correct:
	 * 1 - lhs and rhs must be INT  (Arithmetic BinOps)
	 * 2 - lhs and rhs must be of equal type, which must be either INT or CHAR (>=, >, <, <=)
	 * 3 - always correct (== and !=)
	 * 4 - lhs and rhs must be BOOL (&& and ||)
	 */
	
	// 1:
	public static final WACCBinOp MUL = new WACCArithBinOp();
	public static final WACCBinOp DIV = new WACCArithBinOp();
	public static final WACCBinOp MOD = new WACCArithBinOp();
	public static final WACCBinOp ADD = new WACCArithBinOp();
	public static final WACCBinOp SUB = new WACCArithBinOp();
	
	// 2:
	public static final WACCBinOp GRT = new WACCCompBinOp();
	public static final WACCBinOp GRT_EQ = new WACCCompBinOp();
	public static final WACCBinOp LESS = new WACCCompBinOp();
	public static final WACCBinOp LESS_EQ = new WACCCompBinOp();
	
	// 3:
	public static final WACCBinOp EQ = new WACCEqualBinOp();
	public static final WACCBinOp NOT_EQ = new WACCEqualBinOp();
	
	// 4:
	public static final WACCBinOp AND = new WACCBoolBinOp();
	public static final WACCBinOp OR = new WACCBoolBinOp();
	
	/*
	 * Utility method to convert a String to a Binary Operator
	 */
	public static WACCBinOp evalBinOp(String operator) {
		switch (operator) {
		// 1:
		case "*":
			return MUL;
		case "/":
			return DIV;
		case "%":
			return MOD;
		case "+":
			return ADD;
		case "-":
			return SUB;
		// 2:
		case ">":
			return GRT;
		case ">=":
			return GRT_EQ;
		case "<":
			return LESS;
		case "<=":
			return LESS_EQ;
		// 3:
		case "==":
			return EQ;
		case "!=":
			return NOT_EQ;
		// 4:
		case "&&":
			return AND;
		case "||":
			return OR;

		default:
			throw new IllegalArgumentException("The provided String does not match any operators: " + operator);
		}
	}
	
}
