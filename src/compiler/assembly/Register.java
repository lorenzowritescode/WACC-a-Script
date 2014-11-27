package assembly;

public class Register {
	
	private String varName;
	private Integer registerNumber;
	
	public Register(String varName) {
		this.varName = varName;
	}
	
	public void setRegister(int reg) {
		this.registerNumber = reg;
	}
	
	public Register() {
		varName = null;
	}
	
	@Override
	public String toString() {
		if (registerNumber == null) {
			throw new IllegalStateException("The register for this object was not set.");
		}
		
		return "r" + registerNumber;
	}

}
