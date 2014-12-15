package jsparser;

public class LibFunc {
	
	private String functionName;
	private LibType returnType;
	private LibArgList argList;
	
	public LibFunc(String functionName, LibType returnType, LibArgList argList) {
		this.functionName = functionName;
		this.returnType = returnType;
		this.argList = argList;
	}
	

}
