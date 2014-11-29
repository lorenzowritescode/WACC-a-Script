package assembly.tokens;

import assembly.InstrToken;

public class EmptyToken extends InstrToken {
	public EmptyToken() {}

	@Override
	public String toString() {
		return "";
	}
}
