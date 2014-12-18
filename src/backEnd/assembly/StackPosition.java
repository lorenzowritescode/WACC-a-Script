package assembly;

import assembly.tokens.LoadAddressToken;
import assembly.tokens.StoreToken;

public class StackPosition {
	private static final int WORD_SIZE = 4;

	private int position;
	private Register sourceRegister;
	
	public StackPosition(int i) {
		this.position = i;
		this.sourceRegister = Register.sp;
	}
	
	public StackPosition(int i, Register r) {
		this.position = i;
		this.sourceRegister = r;
	}

	public TokenSequence toAssembly(final Register r) {
		return new TokenSequence().append(
				new LoadAddressToken(r, sourceRegister, position*WORD_SIZE));
	}
	
	/**
	 * @param r The register which is to be stored at the stackPosition
	 * @return the code corresponding to storing the given register
	 */
	public TokenSequence toStoreAssembly(final Register r) {
		return new TokenSequence().append(
				new StoreToken(sourceRegister, r, position*WORD_SIZE));
	}
	
	@Override
	public String toString() {
		return position == 0 ? "[" + sourceRegister.toString() + "]"
				: "[" + sourceRegister.toString() + ", #" + position + "]";
	}
	
	public int getStackIndex() {
		return position * WORD_SIZE;
	}

	public Register getBaseReg() {
		return sourceRegister;
	}
}