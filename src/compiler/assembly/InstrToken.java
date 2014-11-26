package assembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class InstrToken {
	private InstrToken next;
	
	public void setNext(InstrToken t) {
		if (next == null) {
			next = t;
		} else {
			throw new IllegalAccessError("You can't set the next token more than once.\n"
					+ "Token given: " + t.toString());
		}
	}
	
	public List<InstrToken> flatten() {
		List<InstrToken> tokens = new ArrayList<>();
		tokens.add(this);
		tokens.addAll(next.flatten());
		return tokens;
	}

	public List<InstrToken> toPrepend() {
		// normally, there's nothing to prepend
		return null;
	}

	public List<InstrToken> toAppend() {
		// normally, there's nothing to append
		return null;
	}

}
