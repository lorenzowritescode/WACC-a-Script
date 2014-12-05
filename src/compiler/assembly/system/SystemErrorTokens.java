package assembly.system;

import assembly.InstrToken;
import assembly.TokenSequence;

public class SystemErrorTokens {

	public static final InstrToken CHECK_BOUNDS = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(
					SystemErrorTokens.OUT_OF_BOUNDS_MESSAGE,
					SystemFormatterTokens.STRING_FORMATTER);
		}
		
		@Override
		public String toString() {
			return "p_check_array_bounds:"
						+ "\n\tPUSH {lr}"
						+ "\n\tCMP r0, #0"
						+ "\n\tLDRLT r0, =msg_10"
						+ "\n\tBLLT p_throw_runtime_error"
						+ "\n\tLDR r1, [r1]"
						+ "\n\tCMP r0, r1"
						+ "\n\tLDRCS r0, =msg_11"
						+ "\n\tBLCS p_throw_runtime_error"
						+ "\n\tPOP {pc}";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static InstrToken OVERFLOW_ERROR = new InstrToken() {

		public TokenSequence toPrepend() {
			return new TokenSequence(
					SystemErrorTokens.OVERFLOW_MESSAGE, 
					SystemFormatterTokens.STRING_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_throw_overflow_error:"
					+ "\n\tLDR r0, =msg_5"
					+ "\n\tBL p_throw_runtime_error";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static InstrToken DIVIDE_BY_ZERO_ERROR = new InstrToken() {

		public TokenSequence toPrepend() {
			return new TokenSequence(
					SystemErrorTokens.DIVIDE_BY_ZERO_MESSAGE, 
					SystemFormatterTokens.STRING_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_check_divide_by_zero:"
					+ "\n\tPUSH {lr}"
					+ "\n\tCMP r1, #0"
					+ "\n\tLDREQ r0, =msg_6"
					+ "\n\tBLEQ p_throw_runtime_error"
					+ "\n\tPOP {pc}";
		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static InstrToken RUNTIME_ERROR = new InstrToken() {

		@Override
		public String toString() {
			return "p_throw_runtime_error:"
					+ "\n\tBL p_print_string"
					+ "\n\tMOV r0, #-1"
					+ "\n\tBL exit";

		}

		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken NULL_REFERENCE_MESSAGE = new InstrToken() {
		@Override
		public String toString() {
			return "msg_8:"
					+ "\n\t.word 50"
					+ "\n\t.ascii \"NullReferenceError: "
					+ "dereference a null reference\\n\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken DIVIDE_BY_ZERO_MESSAGE = new InstrToken() {
		@Override
		public String toString() {
			return "msg_6:"
					+ "\n\t.word 45"
					+ "\n\t.ascii	\"DivideByZeroError: divide or modulo by zero\\n\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken OVERFLOW_MESSAGE = new InstrToken() {
		@Override
		public String toString() {
			return "msg_5:"
					+ "\n\t.word 82"
					+ "\n\t.ascii	\"OverflowError: the result is too small/large "
					+ "to store in a 4-byte signed-integer.\\n\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static final InstrToken OUT_OF_BOUNDS_MESSAGE = new InstrToken() {
		@Override
		public String toString() {
			return "msg_10:"
					+ "\n\t.word 44"
					+ "\n\t.ascii	\"ArrayIndexOutOfBoundsError: negative index\\n\\0\""
					+"msg_11:"
					+ "\n\t.word 45"
					+ "\n\t.ascii	\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};

}
