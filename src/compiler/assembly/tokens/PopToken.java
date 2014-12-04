package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;

public class PopToken extends InstrToken {
	
    private Register reg;
	
	public PopToken(Register reg) {
		this.reg = reg;
		this.addRegister(reg);
	}
	
	@Override
	public String toString() {
		return "POP " + "{" + reg.toString() + "}";
	}

}
