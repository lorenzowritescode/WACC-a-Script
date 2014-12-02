package assembly;

public class TokenCollector {
	private TokenSequence top;
	private TokenSequence body;
	private TokenSequence bottom;
	
	private RegisterAllocator allocator;
	
	public TokenCollector(TokenSequence progToken) {
		this.top = new TokenSequence().setUnique();
		this.body = progToken;
		this.bottom = new TokenSequence().setUnique();
		this.allocator = new RegisterAllocator();
	}
	
	public TokenSequence collect() {
		
		for (InstrToken t:body) {
			t.setRegisters(allocator);
			top.appendAll(t.toPrepend());
			bottom.appendAll(t.toAppend());
		}

		wrapTopSequence(top);
		TokenSequence finalSequence = new TokenSequence(top, body, bottom);
		
		return finalSequence;
	}
	
	private static void wrapTopSequence(TokenSequence top) {
		top.prepend(new InstrToken() {
			@Override
			public String toString() {
				return ".data\n";
			}
		});
	}
}
