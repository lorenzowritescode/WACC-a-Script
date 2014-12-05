package assembly.system;

import assembly.InstrToken;
import assembly.TokenSequence;

public class SystemPrintTokens {
public static InstrToken PRINT_STRING = new InstrToken() {
		
		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterTokens.STRING_FORMATTER);
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
			return new TokenSequence(SystemFormatterTokens.NEW_LINE_FORMATTER);
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
			return new TokenSequence(SystemFormatterTokens.BOOL_FORMATTER);
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
			return new TokenSequence(SystemFormatterTokens.INT_FORMATTER);
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
	
	public static InstrToken PRINT_REF = new InstrToken() {
		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterTokens.REFERENCE_FORMATTER);
		}
		
		@Override
		public String toString() {
			return "p_print_reference:"
						+ "\n\tPUSH {lr}"
						+ "\n\tMOV r1, r0"
						+ "\n\tLDR r0, =msg_9"
						+ "\n\tADD r0, r0, #4"
						+ "\n\tBL printf"
						+ "\n\tMOV r0, #0"
						+ "\n\tBL fflush"
						+ "\n\tPOP {pc}";	
		}
	};
}
