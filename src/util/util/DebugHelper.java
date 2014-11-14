package util;

public class DebugHelper {
	private boolean testEnvironment;
	
	public DebugHelper() {
		this.testEnvironment = true;
	}
	
	public void turnOff() {
		this.testEnvironment = false;
	}
	
	public void print(String s) {
		if(testEnvironment)
			System.out.println(s);
	}
}
