package tree.type;

import assembly.InstrToken;
import assembly.Register;
import assembly.StackPosition;
import assembly.TokenSequence;
import assembly.tokens.LoadAddressToken;
import assembly.tokens.PrintReferenceToken;
import assembly.tokens.StorePreIndexToken;
import assembly.tokens.StoreToken;
import WACCExceptions.IncompatibleTypesException;

/* Class for the Pair Type
 * Contains type info for 1st and 2nd elements and functionality for NULLs
 * Functionality to check other types are compatible with the pair
 * Rule: 'pair' '(' pair-elem-type ',' pair-elem-type ')'
 */

public class PairType extends WACCType {
	
	private static PairType NULL_PAIR = new PairType(WACCType.NULL, WACCType.NULL);
	private WACCType fst;
	private WACCType snd;
	private final int VAR_SIZE = 4;

	public PairType(WACCType fst, WACCType snd) {
		this.fst = (fst instanceof PairType) ? NULL_PAIR : fst;
		this.snd = (snd instanceof PairType) ? NULL_PAIR : snd;
	}

	public WACCType getFstType() {
		return fst;
	}

	public WACCType getSndType() {
		return snd;
	}
	
	@Override
	public boolean isCompatible(WACCType other) {
		// NULL can be assigned to pairs
		if (other == WACCType.NULL) {
			return true;
		}
		
		if (!(other instanceof PairType)) {
			return false;
		}
		
		PairType otherPair = (PairType) other;
		
		return 	checkType(this.getFstType(), otherPair.getFstType()) 
				&& checkType(this.getFstType(), otherPair.getFstType());
	}
	
	// utility method for checking pair types
	private static boolean checkType(WACCType lhs, WACCType rhs) {
		boolean compatible = lhs == WACCType.NULL || lhs.isCompatible(rhs);
		if (!compatible) {
			new IncompatibleTypesException("The types " + lhs.toString() + " and " + rhs.toString() + "are incompatible.");
		}
		return compatible;
	}

	@Override
	public String toString() {
		return "pair("+fst.toString()+", "+snd.toString()+")";
	}

	@Override
	public InstrToken passAsArg(Register r) {
		return new StorePreIndexToken(Register.sp, r, -VAR_SIZE  );
	}
	
	@Override
	public int getVarSize() {
		return VAR_SIZE;
	}
	
	@Override
	public StoreToken storeAssembly(Register dest, Register source) {
		return new StoreToken(dest, source);
	}

	@Override
	public TokenSequence printAssembly(Register register) {
		return new TokenSequence(new PrintReferenceToken(register));
	}
	
	@Override
	public TokenSequence storeAssembly(Register source, StackPosition pos) {
		int index = pos.getStackIndex();
		return new TokenSequence(
				new StoreToken(Register.sp, source, index));
	}
	
	@Override
	public LoadAddressToken loadAssembly(Register dest, Register source) {
		return new LoadAddressToken(dest, source);
	}
}
