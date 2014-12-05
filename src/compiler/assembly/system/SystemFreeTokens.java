package assembly.system;

import assembly.InstrToken;
import assembly.TokenSequence;

public class SystemFreeTokens {
	public static InstrToken FREE_PAIR = new InstrToken() {
		public TokenSequence toPrepend() {
			return new TokenSequence(SystemErrorTokens.NULL_REFERENCE_MESSAGE, SystemFormatterTokens.STRING_FORMATTER);
		}
		
		public TokenSequence toAppend() {
			return new TokenSequence(SystemErrorTokens.RUNTIME_ERROR, SystemPrintTokens.PRINT_STRING);
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
			return new TokenSequence(SystemErrorTokens.NULL_REFERENCE_MESSAGE, SystemFormatterTokens.STRING_FORMATTER);
		}
		
		public TokenSequence toAppend() {
			return new TokenSequence(SystemErrorTokens.RUNTIME_ERROR, SystemPrintTokens.PRINT_STRING);
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
