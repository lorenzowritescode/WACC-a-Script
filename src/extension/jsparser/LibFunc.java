package jsparser;

public class LibFunc {
	
	private String functionName;
	private LibBaseType returnType;
	private LibArgList argList;
	
	public LibFunc(String functionName, LibBaseType returnType, LibArgList argList) {
		this.functionName = functionName;
		this.returnType = returnType;
		this.argList = argList;
	}

	public String getFunctionName() {
		return functionName;
	}

	public LibBaseType getReturnType() {
		return returnType;
	}

	public LibArgList getArgList() {
		return argList;
	}
	
	@Override
	public String toString() {
		String res = functionName + " :: ";
		for (LibArg a:argList) {
			res += a.toString() + " -> ";
		}
		res += returnType.toString();
		
		return res;		
	}
	
	public LibArgList getArgList() {
		return argList;
	}
	
	public LibType getReturnType() {
		return returnType;
	}
	
	public String getFunctionName() {
		return functionName;
	}

}
