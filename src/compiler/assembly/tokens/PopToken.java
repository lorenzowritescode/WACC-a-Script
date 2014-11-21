package assembly.tokens;

import assembly.InstrToken;
import assembly.RegList;

public class PopToken extends InstrToken {
	
    private RegList regList;
	
	public PopToken(RegList regList) {
		this.regList = regList;
	}
	
	@Override
	public String toString() {
		return "POP " + regList.toString();
	}

}
