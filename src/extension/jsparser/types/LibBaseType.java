package jsparser;

public enum LibBaseType implements LibType {
	NUMBER("number"), CHAR("char"), STRING("string"), BOOL("boolean");
	
	private String s;

	private LibBaseType(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return s;
	}
	
	public static LibBaseType parse(String text) {
		for(LibBaseType t:values()) {
			if (t.s.equals(text))
				return t;
		}
		throw new JSParserException("The provided type `" + text + "` was not recognized.");
	}

	@Override
	public String getTypeString() {
		return s;
	}
}
