package tree.type;

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
		if (!(other instanceof PairType)) {
			return false;
		}
		PairType otherPair = (PairType) other;
		if (!fst.isCompatible(otherPair.getFst()) && !snd.isCompatible(otherPair.getSnd())) {
			return false;
		}
		return true;
	}

}
