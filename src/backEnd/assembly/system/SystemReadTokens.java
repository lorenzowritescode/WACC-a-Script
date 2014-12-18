package assembly.system;

import assembly.InstrToken;
import assembly.TokenSequence;

public class SystemReadTokens {
	public static InstrToken READ_INT = new InstrToken() {

		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterTokens.INT_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_read_int:"
					+ "\n\t\tPUSH {lr}"
					+ "\n\t\tMOV r1, r0"
					+ "\n\t\tLDR r0, =msg_4"
					+ "\n\t\tADD r0, r0, #4"
					+ "\n\t\tBL scanf"
					+ "\n\t\tPOP {pc}";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};

	public static InstrToken READ_CHAR = new InstrToken() {

		public TokenSequence toPrepend() {
			return new TokenSequence(SystemFormatterTokens.CHAR_FORMATTER);
		}

		@Override
		public String toString() {
			return "p_read_char:"
					+ "\n\t\tPUSH {lr}"
					+ "\n\t\tMOV r1, r0"
					+ "\n\t\tLDR r0, =msg_7"
					+ "\n\t\tADD r0, r0, #4"
					+ "\n\t\tBL scanf"
					+ "\n\t\tPOP {pc}";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
