package assembly;


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
		
		wrapTopSequence(top);
		wrapMainBody(body);
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

	private static void wrapMainBody(TokenSequence body) {
		InstrToken mainHeader = new InstrToken() {
			@Override
			public String toString() {
				return ".text\n\n"
						+ ".global main\n"
						+ "main:\n"
						+ "PUSH {lr}";
			}
		};
		
		InstrToken mainFooter = new InstrToken() {
			@Override
			public String toString() {
				return "LDR r0, =0\n"
						+ "POP {pc}\n"
						+ ".ltorg";
			}
		};
		
		body.prepend(mainHeader);
		body.append(mainFooter);
	}
}
