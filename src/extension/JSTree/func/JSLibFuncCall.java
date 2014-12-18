package JSTree.func;

import JSTree.stat.JSStat;

public class JSLibFuncCall extends JSStat {
	
	private String dependency;
	private String fName;
	private JSArgList args;
	
	public JSLibFuncCall(String dependency, String functionName, JSArgList args) {
		this.dependency = dependency;
		this.fName = functionName;
		this.args = args;
	}

	@Override
	public String toCode() {
		return dependency + fName + args.toCode();
	}

}
