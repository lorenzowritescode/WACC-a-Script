package tree.type;

public class PairTypeNode extends WACCType {

	private WACCType fst;
	private WACCType snd;

	public PairTypeNode(WACCType fst, WACCType snd) {
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
		if (!(other instanceof PairTypeNode)) {
			return false;
		}
		PairTypeNode otherPair = (PairTypeNode) other;
		if (!(fst == null) && !(snd == null)) {
			if (!fst.isCompatible(otherPair.getFst()) || !snd.isCompatible(otherPair.getSnd())) {
				return false;
			}
			return true;
		} else if (fst == null) {
			//if fst is null, only snd needs to be checked
			if (!snd.isCompatible(otherPair.getSnd())) {
				return false;
			}
			return true;
		} else {
			//if snd is null, only fst needs to be checked
			if (!fst.isCompatible(otherPair.getFst())) {
				return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		return "pair";
	}

}
