package assembly;

public class Register {
	
	private String regName;
	
	public Register(String regName) {
		this.regName = regName;
	}
	
	@Override
	public String toString() {
		return regName;
	}

}
