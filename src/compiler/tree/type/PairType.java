package tree.type;

import WACCExceptions.IncompatibleTypesException;

public class PairType extends WACCType {
	
	/* Class for the Pair Type
	 * Contains type info for 1st and 2nd elements and functionality for NULLs
	 * Functionality to check other types are compatible with the pair
	 */
	
	private static PairType NULL_PAIR = new PairType(WACCType.NULL, WACCType.NULL);
	private WACCType fst;
	private WACCType snd;

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

}
