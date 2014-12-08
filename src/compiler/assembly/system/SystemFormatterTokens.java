package assembly.system;

import assembly.InstrToken;

public class SystemFormatterTokens {

	public static InstrToken STRING_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_0:"
					+ "\n\t\t.word 5"
					+ "\n\t\t.ascii	\"%.*s\\0\"";
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
					+ "\n\t\t.word 1"
					+ "\n\t\t.ascii	\"\\0\"";
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
					+"\n\t\t.word 5"
					+"\n\t\t.ascii	\"true\\0\""
					+"\n\n\tmsg_3:"
					+"\n\t\t.word 6"
					+"\n\t\t.ascii	\"false\\0\";";
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
					+ "\n\t\t.word 3"
					+ "\n\t\t.ascii	\"%d\\0\"";
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
					+ "\n\t\t.word 4"
					+ "\n\t\t.ascii	\" %c\\0\"";
		}
		
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
	
	public static InstrToken REFERENCE_FORMATTER = new InstrToken() {
		@Override
		public String toString() {
			return "msg_9:"
						+ "\n\t\t.word 3"
						+ "\n\t\t.ascii	\"%p\\0\"";
		}
		@Override
		public boolean requiresTab() {
			return false;
		}
	};
}
