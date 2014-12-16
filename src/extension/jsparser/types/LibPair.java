package jsparser;

public class LibPair implements LibType {

	private LibType fst;
	private LibType snd;

	public LibPair(LibType fst, LibType snd) {
		this.fst = fst;
		this.snd = snd;
	}

	@Override
	public String getTypeString() {
		return "pair<" + fst.getTypeString() + ", " + snd.getTypeString() + ">";
	}
	
	@Override
	public String toString() {
		return getTypeString();
	}

}
