package assembly;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class TokenSequence implements Iterable<InstrToken> {
	private Deque<InstrToken> tokens;
	
	public TokenSequence() {
		tokens = new ArrayDeque<>();
	}
	
	public TokenSequence(InstrToken... instrTokens) {
		tokens = new ArrayDeque<>(Arrays.asList(instrTokens));
	}
	
	public TokenSequence(TokenSequence... sequences) {
		tokens = new ArrayDeque<>();
		
		for (TokenSequence seq:sequences) {
			for (InstrToken t:seq) {
				this.append(t);
			}
		}
	}
	
	public void append(InstrToken t) {
		tokens.addLast(t);
	}
	
	public void prepend(InstrToken t) {
		tokens.addFirst(t);
	}
	
	public void appendAll(TokenSequence ts) {
		for (InstrToken t:ts) {
			this.append(t);
		}
	}
	
	public void prependAll(TokenSequence ts) {
		for (InstrToken t:ts) {
			this.prepend(t);
		}
	}

	@Override
	public Iterator<InstrToken> iterator() {
		return tokens.iterator();
	}
}
