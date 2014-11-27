package assembly;

import java.util.ArrayList;
import java.util.List;

public abstract class InstrToken {
	private List<Register> regs;
	
	public InstrToken() {
		regs = new ArrayList<>();
	}

	/**
	 * @return the TokenSequence containing the data that this InstrToken needs
	 */
	public TokenSequence toPrepend() {
		// normally, there's nothing to prepend
		return null;
	}

	/** This is used to signal that this token has some kind of dependencies, such as system method calls.
	 * @return the TokenSequence containing the InstrToken this method depends on.
	 */
	public TokenSequence toAppend() {
		// normally, there's nothing to append
		return null;
	}
	
	/**
	 * 	This method is used to concretise the abstract representation of Registers.
	 * @param a
	 * 	The RegisterAllocator that will provide the register number (int).
	 * 	Currently this returns 4, 5, 6, etc.
	 */
	public void setRegisters(RegisterAllocator alloc) {
		for (Register r:regs) {
			alloc.allocate(r);
		}
	}

}
