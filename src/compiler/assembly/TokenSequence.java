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
	
	public TokenSequence append(InstrToken t) {
		tokens.addLast(t);
		return this;
	}
	
	public TokenSequence prepend(InstrToken t) {
		tokens.addFirst(t);
		return this;
	}
	
	public TokenSequence appendAll(TokenSequence ts) {
		for (InstrToken t:ts) {
			this.append(t);
		}
		return this;
	}
	
	public TokenSequence prependAll(TokenSequence ts) {
		for (InstrToken t:ts) {
			this.prepend(t);
		}
		return this;
	}

	@Override
	public Iterator<InstrToken> iterator() {
		return tokens.iterator();
	}
	
	@Override
	public String toString() {
		String result = "";
		for (InstrToken t:tokens) {
			result += t.toString() + "\n";
		}
		return result;
	}
}
