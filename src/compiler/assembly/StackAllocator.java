package assembly;

import java.util.Stack;

import assembly.tokens.EmptyToken;

public class StackAllocator {
	
	private static final int WORD_SIZE = 4;
	
	private Stack<Integer> scopeStack;
	
	public StackAllocator() {
		scopeStack = new Stack<>();
		scopeStack.push(0);
	}
	
	public StackPosition allocate() {
		StackPosition pos = new StackPosition(getCounter() * WORD_SIZE);
		incrementCounter();
		return pos;
	}
	
	private void incrementCounter() {
		scopeStack.push( scopeStack.pop() + 1 );
	}

	public void enterNewScope() {
		scopeStack.push(0);
	}
	
	public int getCounter() {
		return scopeStack.peek();
	}
	
	public void exitScope() {
		scopeStack.pop();
	}
	
	public InstrToken getInitialisation() {
		if (getCounter() == 0)
			return new EmptyToken();
		
		return new InstrToken() {
			@Override
			public String toString() {
				return "SUB sp, sp, #" + getCounter() * WORD_SIZE;
			}
		};
	}
	
	public InstrToken getTermination() {
		if (getCounter() == 0)
			return new EmptyToken();
		
		return new InstrToken() {
			@Override
			public String toString() {
				return "ADD sp, sp, #" + getCounter() * WORD_SIZE;
			}
		};
	}
}
