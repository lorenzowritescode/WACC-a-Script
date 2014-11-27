package assembly;

import java.util.Arrays;
import java.util.List;


public class SystemTokens {
	public static InstrToken PRINT_STRING = new InstrToken() {
		
		public List<InstrToken> toPrepend() {
			/*
			 * Print string needs the string formatter to be inserted at the top of the assembly file;
			 */
			return makeList(new InstrToken() {
				@Override
				public String toString() {
					return "msg_1:"
							+ "\n\t.word 5"
							+ "\n\t.ascii	\"%.*s\\0\"";
				}
			});
		}
		
		@Override
		public String toString() {
			return	"p_print_string:"
						+"\n\tPUSH {lr}"
						+"\n\tLDR r1, [r0]"
						+"\n\tADD r2, r0, #4"
						+"\n\tLDR r0, =msg_1"
						+"\n\tADD r0, r0, #4"
						+"\n\tBL printf"
						+"\n\tMOV r0, #0"
						+"\n\tBL fflush"
						+"\n\tPOP {pc}";
		}
	};
	
	public static InstrToken PRINT_LN = new InstrToken() {
		
		public List<InstrToken> toPrepend() {
			/*
			 * Print string needs the string formatter to be inserted at the top of the assembly file;
			 */
			return makeList(new InstrToken() {
				@Override
				public String toString() {
					return "msg_2:"
							+ "\n\t.word 1"
							+ "\n\t.ascii	\"\\0\"";
				}
			});
		}
		
		@Override
		public String toString() {
			return	"p_print_ln:"
						+"\n\tPUSH {lr}"
						+"\n\tLDR r0, =msg_2"
						+"\n\tADD r0, r0, #4"
						+"\n\tBL puts"
						+"\n\tMOV r0, #0"
						+"\n\tBL fflush"
						+"\n\tPOP {pc}";
		}
	};
	
	public class PrintStringToken extends InstrToken {
		
		private String s;
		private Register r;

		public PrintStringToken(String s, Register r) {
			this.s = s;
		}
		
		@Override
		public List<InstrToken> toPrepend() {
			return makeList(
					PRINT_STRING.toPrepend(),
					new InstrToken() {
						@Override
						public String toString() {
							return 
						}
					});
		}

		@Override
		public List<InstrToken> toAppend() {
			return makeList(PRINT_STRING);
		}
		
	}
	
	private static List<InstrToken> makeList(InstrToken... instrTokens ) {
		return Arrays.asList(instrTokens);
	}
}
