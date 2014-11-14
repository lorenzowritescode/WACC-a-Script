package util;

import org.apache.commons.cli.CommandLine;

public class DebugHelper {
	private boolean testEnvironment;
	private boolean debuggingEnvironment;
	
	public DebugHelper() {
		this.testEnvironment = false;
		this.debuggingEnvironment = false;
	}
	
	public void printV(String s) {
		if(testEnvironment)
			System.out.println(s);
	}
	
	public void printD(String s) {
		if(debuggingEnvironment)
			System.out.println(s);
	}
	
	public void setOptions(CommandLine cmd) {

		// Turn on verbose logging if -v flag is present
		if (cmd.hasOption("v")) {
			this.testEnvironment = true;
		}
		// Turn on debugging logging if -d flag is present
		if (cmd.hasOption("d")) {
			this.debuggingEnvironment = true;
		}
	}
}
