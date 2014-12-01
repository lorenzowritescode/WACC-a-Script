package assembly;

import java.util.HashSet;


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
		top = removeDuplicates(top);
		bottom = removeDuplicates(bottom);
		wrapTopSequence(top);
		wrapMainBody(body);
		TokenSequence finalSequence = new TokenSequence(top, body, bottom);
		
		return finalSequence;
	}
	
	/**
	 * @param seq the token sequence from which any duplicate tokens 
	 * will be removed
	 * @return Returns a token sequence with no duplicate elements
	 */
	private TokenSequence removeDuplicates(TokenSequence seq) {
		HashSet<InstrToken> instrSet = new HashSet<InstrToken>(); 
		for (InstrToken tok:seq) {
			instrSet.add(tok);
		}
		TokenSequence fin = new TokenSequence();
		for (InstrToken tok:instrSet) {
			fin.append(tok);
		}
		return fin;
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
						+ ".global main\n";
			}
		};
		
		InstrToken mainFooter = new InstrToken() {
			@Override
			public String toString() {
				return "\t.ltorg";
			}
		};
		
		body.prepend(mainHeader);
		body.append(mainFooter);
	}
}
