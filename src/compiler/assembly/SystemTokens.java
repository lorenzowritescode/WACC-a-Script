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
		
		@Override
		public boolean requiresTab() {
			return false;
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
		
		@Override
		public boolean requiresTab() {
			return false;
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
		
		@Override
		public boolean requiresTab() {
			return false;
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
		
		@Override
		public boolean requiresTab() {
			return false;
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
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
    public static InstrToken DIVIDE_BY_ZERO_ERROR = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(DIVIDE_BY_ZERO_MESSAGE, STRING_FORMATTER);
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
	
	public static InstrToken STRING_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_0:"
					+ "\n\t.word 5"
					+ "\n\t.ascii	\"%.*s\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken NEW_LINE_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_1:"
					+ "\n\t.word 1"
					+ "\n\t.ascii	\"\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
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
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken INT_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_4:"
					+ "\n\t.word 3"
					+ "\n\t.ascii	\"%d\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken CHAR_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_7:"
					+ "\n\t.word 4"
					+ "\n\t.ascii	\"%.c\\0\"";
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
	
	public static InstrToken READ_INT = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(INT_FORMATTER);
		}
		
		@Override
		public String toString() {
			return "p_read_int:"
						+ "\n\tPUSH {lr}"
						+ "\n\tMOV r1, r0"
						+ "\n\tLDR r0, =msg_4"
						+ "\n\tADD r0, r0, #4"
						+ "\n\tBL scanf"
						+ "\n\tPOP {pc}";
		}
	};
	
	public static InstrToken READ_CHAR = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(CHAR_FORMATTER);
		}
		
	    @Override
		public String toString() {
			return "p_read_char:"
						+ "\n\tPUSH {lr}"
						+ "\n\tMOV r1, r0"
						+ "\n\tLDR r0, =msg_7"
						+ "\n\tADD r0, r0, #4"
						+ "\n\tBL scanf"
						+ "\n\tPOP {pc}";
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
	};
	
	public static InstrToken FREE_PAIR = new InstrToken() {
		public TokenSequence toPrepend() {
			return new TokenSequence(NULL_REFERENCE_MESSAGE, STRING_FORMATTER);
		}
		
		public TokenSequence toAppend() {
			return new TokenSequence(RUNTIME_ERROR, PRINT_STRING);
		}
		
		@Override
		public String toString() {
			return "p_free_pair:"
					   + "\n\tPUSH {lr}"
					   + "\n\tCMP r0, #0"
					   + "\n\tLDREQ r0, =msg_8"
					   + "\n\tBEQ p_throw_runtime_error"
					   + "\n\tPUSH {r0}"
					   + "\n\tLDR r0, [r0]"
					   + "\n\tBL free"
					   + "\n\tLDR r0, [sp]"
					   + "\n\tLDR r0, [r0, #4]"
					   + "\n\tBL free"
					   + "\n\tPOP {r0}"
					   + "\n\tBL free"
					   + "\n\tPOP {pc}";
		}
	};
	
	public static InstrToken FREE_ARRAY = new InstrToken() {
		public TokenSequence toPrepend() {
			return new TokenSequence(NULL_REFERENCE_MESSAGE, STRING_FORMATTER);
		}
		
		public TokenSequence toAppend() {
			return new TokenSequence(RUNTIME_ERROR, PRINT_STRING);
		}
		
		@Override
		public String toString() {
			return "p_free_array:"
						+ "\n\tPUSH {lr}"
						+ "\n\tCMP r0, #0"
						+ "\n\tLDREQ r0, =msg_8"
						+ "\n\tBEQ p_throw_runtime_error"
						+ "\n\tBL free"
						+ "\n\tPOP {pc}";
		}
	};
}