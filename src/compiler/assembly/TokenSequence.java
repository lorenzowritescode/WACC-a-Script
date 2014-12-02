package assembly;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class TokenSequence implements Iterable<InstrToken> {
	public static enum MODE {
		ALL, UNIQUE
	}

	public static final TokenSequence EMPTY_SEQUENCE = new TokenSequence();
	
	private Deque<InstrToken> tokens;
	private MODE m;

	
	public TokenSequence() {
		tokens = new ArrayDeque<>();
		this.m = MODE.ALL;
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
	
	public TokenSequence setUnique() {
		this.m = MODE.UNIQUE;
		return this;
	}
	
	public TokenSequence setAcceptAll() {
		this.m = MODE.ALL;
		return this;
	}
	
	public TokenSequence append(InstrToken t) {
		if ( t == null || 
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
		
		tokens.addLast(t);
		return this;
	}
	
	public TokenSequence prepend(InstrToken t) {
		if ( t == null ||
				this.m == MODE.UNIQUE 
				&& tokens.contains(t))
			return this;
			
		tokens.addFirst(t);
		return this;
	}
	
	public TokenSequence appendAll(TokenSequence ts) {
		if (ts == null)
			return this;
		for (InstrToken t:ts) {
			this.append(t);
		}
		
		return this;
	}
	
	public TokenSequence prependAll(TokenSequence ts) {
		if (ts == null)
			return this;
		Iterator<InstrToken> iter = ts.reverseIterator();
		while(iter.hasNext()) {
			this.prepend(iter.next());
		}
		
		return this;
	}
	
	public Iterator<InstrToken> reverseIterator() {
		return tokens.descendingIterator();
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

	public Integer size() {
		return tokens.size();
	}
}
