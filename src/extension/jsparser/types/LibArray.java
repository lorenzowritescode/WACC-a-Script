package jsparser.types;

public class LibArray implements LibType {
	
	private LibType inner;
	
	public LibArray(LibType inner) {
		this.inner = inner;
	}
	
	@Override
	public String toString() {
		return getTypeString();
	}
	
	@Override
	public String getTypeString() {
		return "array<"+inner.getTypeString()+">";
	}
	
	public LibType getInner() {
		return inner;
	}

}
