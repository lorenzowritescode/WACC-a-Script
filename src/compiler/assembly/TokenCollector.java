package assembly;

import java.util.ArrayList;
import java.util.List;

public class TokenCollector {
	private TokenSequence top;
	private TokenSequence body;
	private TokenSequence bottom;
	
	private RegisterAllocator allocator;
	
	public TokenCollector(TokenSequence progToken) {
		this.top = new TokenSequence();
		this.body = progToken;
		this.bottom = new TokenSequence();
		this.allocator = new RegisterAllocator();
	}
	
	public TokenSequence collect() {
		
		for (InstrToken t:body) {
			t.setRegisters(allocator);
			top.appendAll(t.toPrepend());
			bottom.appendAll(t.toAppend());
		}
		
		TokenSequence finalList = new TokenSequence(top, body, bottom);
		
		return finalList;
	}
}
