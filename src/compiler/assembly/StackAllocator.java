package assembly;

import assembly.tokens.EmptyToken;
import assembly.tokens.LoadToken;
import assembly.tokens.StoreToken;

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
	
	
	public class StackPosition {
		private int position;
		
		public StackPosition(int i) {
			this.position = i;
		}
		
		public TokenSequence toAssembly(final Register r) {
			return new TokenSequence().append(
					position == 0?
					new LoadToken(r, Register.sp)
					:new LoadToken(r, Register.sp, position*WORD_SIZE));
					
		}
		
		/**
		 * This method is used when declaring variables, and initially storing 
		 * them in the heap.
		 * 
		 * @param r the register in which the variable is being held
		 * @return Returns the Assembly code used to store a given register 
		 * at the appropriate address
		 */
		public TokenSequence toStoreAssembly(final Register r) {
			return new TokenSequence().append(
					position == 0?
							new StoreToken(r, Register.sp)
							:new StoreToken(r, Register.sp, position*WORD_SIZE));
		}
	}
}
