package assembly;

import java.util.HashMap;
import java.util.Map;

public class RegisterAllocator {
	
	private int currentRegister;
	private Map<Register, Integer> regMap;
	
	public RegisterAllocator() {
		this.currentRegister = 4;
		regMap = new HashMap<>();
	}
	
	/**
	 * @return next free general register
	 */
	private int getGeneralReg() {
		return currentRegister++;
	}

	public void allocate(Register r) {
		if( !regMap.containsKey(r) ) {
			int regIndex = getGeneralReg();
			r.setRegister(regIndex);
			regMap.put(r, regIndex);
		}
	}
	
}
