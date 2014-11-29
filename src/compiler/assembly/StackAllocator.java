package assembly;

import assembly.tokens.EmptyToken;

public class StackAllocator {
	
	private static final int WORD_SIZE = 4;
	
	private int allocation_counter;
	private StackAllocator parent;
	
	public StackAllocator() {
		allocation_counter = 0;
		this.parent = null;
	}
	
	public StackAllocator(StackAllocator parent) {
		this();
		this.parent = parent;
	}
	
	public StackPosition allocate() {
		return new StackPosition(allocation_counter++);
	}
	
	public StackAllocator enterNewScope() {
		return new StackAllocator(this);
	}
	
	public int getDepth() {
		return allocation_counter;
	}
	
	public StackAllocator exitScope() {
		return this.parent;
	}
	
	public InstrToken getInitialisation() {
		if (allocation_counter == 0)
			return new EmptyToken();
		
		return new InstrToken() {
			@Override
			public String toString() {
				return "SUB sp, sp, #" + allocation_counter * WORD_SIZE;
			}
		};
	}
	
	public InstrToken getTermination() {
		if (allocation_counter == 0)
			return new EmptyToken();
		
		return new InstrToken() {
			@Override
			public String toString() {
				return "ADD sp, sp, #" + allocation_counter * WORD_SIZE;
			}
		};
	}
	
	
	private class StackPosition {
		private int position;
		
		public StackPosition(int i) {
			this.position = i;
		}
		
		public TokenSequence toAssembly(final Register r) {
			return new TokenSequence().append(new InstrToken() {
				@Override
				public String toString() {
					return position == 0?
							"LDR " + r.toString() + ", [sp]"
							: "LDR " + r.toString() + ", [sp, #"+ position + "]";
				}
			});
		}
	}
}
