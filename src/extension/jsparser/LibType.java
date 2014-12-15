package jsparser;

public enum LibType {
	NUMBER("number"), CHAR("char"), STRING("string"), BOOL("boolean");
	
	private String s;

	private LibType(String s) {
		this.s = s;
	}

	public static LibType parse(String text) {
		for(LibType t:values()) {
			if (t.s.equals(text))
				return t;
		}
		throw new JSParserException("The provided type was not recognized.");
	}
	
	public String getTypeString() {
		return s;
	}
}
