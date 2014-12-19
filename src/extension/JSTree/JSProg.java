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
		// Loading dependencies
		Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(funcDeps.values());
        ArrayList<String> deps = new ArrayList<>(set);
		String dependencies = "";
		
		for(String filePath : deps) {
			String fileName = new File(filePath).getName();
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
			
			String rawFilePath = filePath.replace("js-lib", "raw-js-lib");
			dependencies += "var " + fileName + " = require('./" + rawFilePath + "');\n";
		}
		
		String functionDecs = "";
		for (JSFunc f:functions) {
			functionDecs += "\n" + f.toCode();
		}
		
		String bodyString = body.toCode();
		
		bodyString += "\ncore.terminate();\n";
		for(int i = 0; i < body.depthIncremented(); i++) {
			bodyString += "})\n";
		}
		
		bodyString += functionDecs;
		
		return dependencies + wrapMainBody(bodyString, !corePath.equalsIgnoreCase("no_core"));
	}
	
	private String wrapMainBody(String body, boolean requiresCore) {
		String coreDec = "";
		String functionDec = "function program (core) {\n";
		String functionEnd = "}\n";
		String moduleExport = "module.export = { program: program }\n";
		
		if (requiresCore) {
			coreDec = "var core = require('" + corePath + "');\n";
			functionDec = "(function() {\n";
			functionEnd = "})();";
			moduleExport = "";
		}
		
		return coreDec + functionDec + body + functionEnd + moduleExport;
	}

	public void setCorePath(String corePath) {
		this.corePath = corePath;
	}

}
