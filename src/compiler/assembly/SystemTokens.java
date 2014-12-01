package assembly;


public class SystemTokens {
	public static InstrToken PRINT_STRING = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(STRING_FORMATTER);
		}
		
		@Override
		public String toString() {
			return	"p_print_string:"
						+"\n\tPUSH {lr}"
						+"\n\tLDR r1, [r0]"
						+"\n\tADD r2, r0, #4"
						+"\n\tLDR r0, =msg_0"
						+"\n\tADD r0, r0, #4"
						+"\n\tBL printf"
						+"\n\tMOV r0, #0"
						+"\n\tBL fflush"
						+"\n\tPOP {pc}";
		}
	};
	
	public static InstrToken PRINT_LN = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(NEW_LINE_FORMATTER);
		}
		
		@Override
		public String toString() {
			return	"p_print_ln:"
						+"\n\tPUSH {lr}"
						+"\n\tLDR r0, =msg_1"
						+"\n\tADD r0, r0, #4"
						+"\n\tBL puts"
						+"\n\tMOV r0, #0"
						+"\n\tBL fflush"
						+"\n\tPOP {pc}";
		}
	};
	
	
	public static InstrToken PRINT_BOOL = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(BOOL_FORMATTER);
		}
		
		@Override
		public String toString() {
			return	"p_print_bool:"
						+"\n\tPUSH {lr}"
						+"\n\tCMP r0, #0"
						+"\n\tLDRNE r0, =msg_2"
						+"\n\tLDREQ r0, =msg_3"
						+"\n\tADD r0, r0, #4"
						+"\n\tBL printf"
						+"\n\tMOV r0, #0"
						+"\n\tBL fflush"
						+"\n\tPOP {pc}";
		}
	};
	


	public static InstrToken PRINT_INT = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(INT_FORMATTER);
		}
		
		@Override
		public String toString() {
			return	"p_print_int:"
						+ "\n\tPUSH {lr}"
						+ "\n\tMOV r1, r0"
						+ "\n\tLDR r0, =msg_4"
						+ "\n\tADD r0, r0, #4"
						+ "\n\tBL printf"
						+ "\n\tMOV r0, #0"
						+ "\n\tBL fflush"
						+ "\n\tPOP {pc}";
		}
	};
	
	public static InstrToken OVERFLOW_ERROR = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(OVERFLOW_MESSAGE, STRING_FORMATTER);
		}
		
		@Override
		public String toString() {
			return "p_throw_overflow_error:"
						+ "\n\tLDR r0, =msg_5"
						+ "\n\tBL p_throw_runtime_error";
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
	};
	
	public static InstrToken STRING_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_0:"
					+ "\n\t.word 5"
					+ "\n\t.ascii	\"%.*s\\0\"";
		}
	};
	
	public static InstrToken NEW_LINE_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_1:"
					+ "\n\t.word 1"
					+ "\n\t.ascii	\"\\0\"";
		}
	};
	
	public static InstrToken BOOL_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_2:"
						+"\n\t.word 5"
						+"\n\t.ascii	\"true\\0\""
					+"\n\nmsg_3:"
					+"\n\t.word 6"
					+"\n\t.ascii	\"false\\0\";";
		}
	};
	
	public static InstrToken INT_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_4:"
					+ "\n\t.word 3"
					+ "\n\t.ascii	\"%d\\0\"";
		}
	};
	
	public static InstrToken OVERFLOW_MESSAGE = new InstrToken() {
		@Override
		public String toString() {
			return "msg_5:"
					+ "\n\t.word 82"
					+ "\n\t.ascii	\"OverflowError: the result is too small/large "
					+ "to store in a 4-byte signed-integer.\n\"";
		}
	};

}