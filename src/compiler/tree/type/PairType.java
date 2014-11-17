package tree.type;

import WACCExceptions.IncompatibleTypesException;

public class PairType extends WACCType {

	private WACCType fst;
	private WACCType snd;

	public PairType(WACCType fst, WACCType snd) {
		this.fst = fst;
		this.snd = snd;
	}

	public WACCType getFst() {
		return fst;
	}

	public WACCType getSnd() {
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
		
		return 	checkType(this.getFst(), otherPair.getFst()) 
				&& checkType(this.getFst(), otherPair.getFst());
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
