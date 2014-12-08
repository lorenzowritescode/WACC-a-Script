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
						+"\n\t\tPUSH {lr}"
						+"\n\t\tLDR r1, [r0]"
						+"\n\t\tADD r2, r0, #4"
						+"\n\t\tLDR r0, =msg_0"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL printf"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
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
						+"\n\t\tPUSH {lr}"
						+"\n\t\tLDR r0, =msg_1"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL puts"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
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
						+"\n\t\tPUSH {lr}"
						+"\n\t\tCMP r0, #0"
						+"\n\t\tLDRNE r0, =msg_2"
						+"\n\t\tLDREQ r0, =msg_3"
						+"\n\t\tADD r0, r0, #4"
						+"\n\t\tBL printf"
						+"\n\t\tMOV r0, #0"
						+"\n\t\tBL fflush"
						+"\n\t\tPOP {pc}";
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
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tMOV r1, r0"
						+ "\n\t\tLDR r0, =msg_4"
						+ "\n\t\tADD r0, r0, #4"
						+ "\n\t\tBL printf"
						+ "\n\t\tMOV r0, #0"
						+ "\n\t\tBL fflush"
						+ "\n\t\tPOP {pc}";
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
						+ "\n\t\tPUSH {lr}"
						+ "\n\t\tMOV r1, r0"
						+ "\n\t\tLDR r0, =msg_9"
						+ "\n\t\tADD r0, r0, #4"
						+ "\n\t\tBL printf"
						+ "\n\t\tMOV r0, #0"
						+ "\n\t\tBL fflush"
						+ "\n\t\tPOP {pc}";	
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
