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
			return new TokenSequence(SystemFormatterTokens.CHAR_FORMATTER);
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
}
