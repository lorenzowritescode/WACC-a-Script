package assembly;

import java.util.ArrayList;

public class RegisterAllocator {
	
	private ArrayList<Register> freeRegs;
	private ArrayList<Register> argRegs;
	
	/**
	 * Fills register lists with all possible registers (i.e. sets all registers to free)
	 */
	public void init() {
		freeRegs.clear();
		argRegs.clear();
		for (int i = 4; i < 13; i++) {
			Register reg = new Register("R" + i);
			freeRegs.add(reg);
		}
		for (int i = 0; i < 4; i++) {
			Register reg = new Register("R" + i);
			argRegs.add(reg);
		}
	}
	
	
	/**
	 * @return returns next free general register
	 */
	public Register getGeneralReg() {
		return freeRegs.remove(0);
	}
	
	
	/**
	 * clears and resets free general register list
	 */
	public void resetGenralRegs() {
		freeRegs.clear();
		for (int i = 4; i < 13; i++) {
			Register reg = new Register("R" + i);
			freeRegs.add(reg);
		}
	}
	
	/**
	 * @return returns next free argument register
	 */
	public Register getArgReg() {
		return argRegs.remove(0);
	}
	
	public void resetArgRegs() {
		argRegs.clear();
		for (int i = 0; i < 4; i++) {
			Register reg = new Register("R" + i);
			argRegs.add(reg);
		}
	}
	
}
