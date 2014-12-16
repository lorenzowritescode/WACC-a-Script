package jsparser;

import jsparser.types.LibType;

public class LibArg {
	private LibType type;
	
	public LibArg(LibType type) {
		this.type = type;
	}
	
	 @Override
	public String toString() {
		 return type.toString();
	}
	
	public LibType getType() {
		return type;
	}
}
