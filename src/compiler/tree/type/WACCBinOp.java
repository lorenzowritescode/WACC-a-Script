package tree.type;

import tree.expr.ExprNode;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.AddToken;
import assembly.tokens.AndToken;
import assembly.tokens.BranchLinkToken;
import assembly.tokens.CompareToken;
import assembly.tokens.DivideByZeroErrorToken;
import assembly.tokens.MovImmToken;
import assembly.tokens.MovRegToken;
import assembly.tokens.MultiplySignedLongToken;
import assembly.tokens.OrToken;
import assembly.tokens.OverflowToken;
import assembly.tokens.SubToken;

/*
 * This class is used to represent the behaviour of WACC Binary Operators.
 * Their role is to check wether two provided expression (`lhs`, `rhs`) can be used in a binary expression with a given operator;
 * They are also used to infer the type of any given binary expression.
 */
public abstract class WACCBinOp {
	
	public WACCBinOp() {}

	public abstract boolean check(ExprNode lhs, ExprNode rhs);

	public abstract WACCType getType();
	
	public abstract TokenSequence apply(Register lhs, Register rhs);

	/*
	 * There are only 4 types of logic to check that `rhs BinOp lhs` is correct:
	 * 1 - lhs and rhs must be INT  (Arithmetic BinOps)
	 * 2 - lhs and rhs must be of equal type, which must be either INT or CHAR (>=, >, <, <=)
	 * 3 - always correct (== and !=)
	 * 4 - lhs and rhs must be BOOL (&& and ||)
	 */
	
	// 1:
	public static final WACCBinOp MUL = new WACCArithBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken mul = new MultiplySignedLongToken(lhs, rhs, lhs, rhs);
			InstrToken cmp = new CompareToken(lhs, rhs, "ASR #31");
			InstrToken overflow = new OverflowToken();
			return new TokenSequence(mul, cmp, overflow);
		}
	};
	public static final WACCBinOp DIV = new WACCArithBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken op1 = new MovRegToken(Register.R0, lhs);
			InstrToken op2 = new MovRegToken(Register.R1, rhs);
			InstrToken divZeroError = new DivideByZeroErrorToken();
			InstrToken div = new BranchLinkToken("__aeabi_idiv");
			InstrToken mov = new MovRegToken(lhs, Register.R0);
			TokenSequence seq = new TokenSequence(op1, op2, divZeroError, div, mov);
			return seq;
		}
		
	};
	public static final WACCBinOp MOD = new WACCArithBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken op1 = new MovRegToken(Register.R0, lhs);
			InstrToken op2 = new MovRegToken(Register.R1, rhs);
			InstrToken divZeroError = new DivideByZeroErrorToken();
			InstrToken div = new BranchLinkToken("__aeabi_idivmod");
			InstrToken mov = new MovRegToken(lhs, Register.R1);
			return new TokenSequence(op1, op2, divZeroError, div, mov);
		}
		
	};
	public static final WACCBinOp ADD = new WACCArithBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken add = new AddToken("S", lhs, lhs, rhs);
			InstrToken overflow = new OverflowToken();
			return new TokenSequence(add, overflow);
		}
		
	};
	public static final WACCBinOp SUB = new WACCArithBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken sub = new SubToken("S", lhs, lhs, rhs);
			InstrToken overflow = new OverflowToken();
			return new TokenSequence(sub, overflow);
		}
		
	};
	
	// 2:
	public static final WACCBinOp GRT = new WACCCompBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken gt = new MovImmToken("GT", lhs, "#1");
			InstrToken leq = new MovImmToken("LE", lhs, "#0");
			return new TokenSequence(cmp, gt, leq);
		}
		
	};
	public static final WACCBinOp GRT_EQ = new WACCCompBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken geq = new MovImmToken("GE", lhs, "#1");
			InstrToken lt = new MovImmToken("LT", lhs, "#0");
			return new TokenSequence(cmp, geq, lt);
		}
		
	};
	public static final WACCBinOp LESS = new WACCCompBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken lt = new MovImmToken("LT", lhs, "#1");
			InstrToken geq = new MovImmToken("GE", lhs, "#0");
			return new TokenSequence(cmp, lt, geq);
		}
		
	};
	public static final WACCBinOp LESS_EQ = new WACCCompBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken leq = new MovImmToken("LE", lhs, "#1");
			InstrToken gt = new MovImmToken("GT", lhs, "#0");
			return new TokenSequence(cmp, leq, gt);
		}
		
	};
	
	// 3:
	public static final WACCBinOp EQ = new WACCEqualBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken eq = new MovImmToken("EQ", lhs, "#1");
			InstrToken neq = new MovImmToken("NE", lhs, "#0");
			return new TokenSequence(cmp, eq, neq);
		}
		
	};
	public static final WACCBinOp NOT_EQ = new WACCEqualBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			InstrToken cmp = new CompareToken(lhs, rhs);
			InstrToken neq = new MovImmToken("NE", lhs, "=1");
			InstrToken eq = new MovImmToken("EQ", lhs, "=0");
			return new TokenSequence(cmp, neq, eq);
		}
		
	};
	
	// 4:
	public static final WACCBinOp AND = new WACCBoolBinOp() {

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			return new TokenSequence(new AndToken(lhs, lhs, rhs));
		}
		
	};
	public static final WACCBinOp OR = new WACCBoolBinOp(){

		@Override
		public TokenSequence apply(Register lhs, Register rhs) {
			return new TokenSequence(new OrToken(lhs, lhs, rhs));
		}
		
	};
	
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
