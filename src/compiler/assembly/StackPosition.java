package assembly;

import assembly.tokens.LoadAddressToken;
import assembly.tokens.StoreToken;

public class StackPosition {
	private int position;
	private static final int WORD_SIZE = 4;
	
	public StackPosition(int i) {
		this.position = i;
	}
	
	public TokenSequence toAssembly(final Register r) {
		return new TokenSequence().append(
				new LoadAddressToken(r, Register.sp, position*WORD_SIZE));
	}
	
	/**
	 * @param r The register which is to be stored at the stackPosition
	 * @return the code corresponding to storing the given register
	 */
	public TokenSequence toStoreAssembly(final Register r) {
		return new TokenSequence().append(
				new StoreToken(r, Register.sp, position*WORD_SIZE));
	}
	
	@Override
	public String toString() {
		return position == 0 ? "[sp]" 
				: "[sp, #" + position + "]";
	}
	
	public int getStackIndex() {
		return position * WORD_SIZE;
	}
}