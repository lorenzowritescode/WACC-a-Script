package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class PushToken extends InstrToken {
	
	private Register reg;
	
	public PushToken(Register reg) {
		this.reg = reg;
		this.addRegister(reg);
	}
	
	@Override
	public String toString() {
		return "PUSH " + "{" +reg.toString() + "}";
	}

}
