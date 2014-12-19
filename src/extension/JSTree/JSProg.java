package JSTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import JSTree.func.JSFunc;
import JSTree.stat.JSStat;

public class JSProg extends JSTree {

	private List<JSFunc> functions;
	private JSStat body;
	private HashMap<String, String> funcDeps;
	private String corePath;
	
	public JSProg(List<JSFunc> functions, JSStat body, HashMap<String, String> funcDeps) {
		this.functions = functions;
		this.body = body;
		this.funcDeps = funcDeps;
	}

	@Override
	public String toCode() {
		String requireCore = "var core = require('" + corePath + "');\n";
		
		Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(funcDeps.values());
        ArrayList<String> deps = new ArrayList<>(set);
		
		for(String filePath : deps) {
			String fileName = new File(filePath).getName();
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
			String rawFilePath = filePath.replace("js-lib", "raw-js-lib");
			requireCore = requireCore + "var " + fileName + " = require('./" + rawFilePath + "');\n";
		}
		
		String bodyString = body.toCode();
		
		String functionDecs = "";
		for (JSFunc f:functions) {
			functionDecs += "\n" + f.toCode();
		}
		
		String result = requireCore + "(function() {\n " + bodyString;
		result += "\ncore.terminate();\n";
		for(int i = 0; i < body.depthIncremented(); i++) {
			result += "})\n";
		}
		
		result += functionDecs + "})();\n";
		
		return result;
	}

	public void setCorePath(String corePath) {
		this.corePath = corePath;
	}

}
