package assembly.tokens;

import assembly.InstrToken;
import assembly.RegList;

public class PushToken extends InstrToken {
	
	private RegList regList;
	
	public PushToken(RegList regList) {
		this.regList = regList;
	}
	
	@Override
	public String toString() {
		return "PUSH " + regList.toString();
	}

}
