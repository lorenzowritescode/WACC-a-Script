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
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tCMP r0, #0"
						+ "\n\t\tLDRLT r0, =msg_10"
						+ "\n\t\tBLLT p_throw_runtime_error"
						+ "\n\t\tLDR r1, [r1]"
						+ "\n\t\tCMP r0, r1"
						+ "\n\t\tLDRCS r0, =msg_11"
						+ "\n\t\tBLCS p_throw_runtime_error"
						+ "\n\t\tPOP {pc}";
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
					+ "\n\t\tLDR r0, =msg_5"
					+ "\n\t\tBL p_throw_runtime_error";
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
					+ "\n\t\tPUSH {lr}"
					+ "\n\t\tCMP r1, #0"
					+ "\n\t\tLDREQ r0, =msg_6"
					+ "\n\t\tBLEQ p_throw_runtime_error"
					+ "\n\t\tPOP {pc}";
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
					+ "\n\t\tBL p_print_string"
					+ "\n\t\tMOV r0, #-1"
					+ "\n\t\tBL exit";

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
					+ "\n\t\t.word 50"
					+ "\n\t\t.ascii \"NullReferenceError: "
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
					+ "\n\t\t.word 45"
					+ "\n\t\t.ascii	\"DivideByZeroError: divide or modulo by zero\\n\\0\"";
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
					+ "\n\t\t.word 82"
					+ "\n\t\t.ascii	\"OverflowError: the result is too small/large "
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
					+ "\n\t\t.word 44"
					+ "\n\t\t.ascii	\"ArrayIndexOutOfBoundsError: negative index\\n\\0\""
					+"\nmsg_11:"
					+ "\n\t\t.word 45"
					+ "\n\t\t.ascii	\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};

}
