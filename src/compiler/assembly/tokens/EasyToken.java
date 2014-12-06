package assembly.tokens;

import assembly.InstrToken;

public class EasyToken extends InstrToken {
	private String assemblyString;

	public EasyToken(String s) {
		this.assemblyString = s;
	}
	
	@Override
	public String toString() {
		return assemblyString;
	}
}
