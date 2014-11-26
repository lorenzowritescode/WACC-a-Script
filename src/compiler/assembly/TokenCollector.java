package assembly;

import java.util.ArrayList;
import java.util.List;

public class TokenCollector {
	private List<InstrToken> top;
	private List<InstrToken> body;
	private List<InstrToken> bottom;
	private InstrToken progToken;
	
	public TokenCollector(InstrToken progToken) {
		this.progToken = progToken;
	}
	
	public List<InstrToken> collect() {
		body = progToken.flatten();
		
		for (InstrToken t:body) {
			top.addAll(t.toPrepend());
			bottom.addAll(t.toAppend());
		}
		
		List<InstrToken> finalList = new ArrayList<>();
		finalList.addAll(top);
		finalList.addAll(body);
		finalList.addAll(bottom);
		
		return finalList;
	}
}
