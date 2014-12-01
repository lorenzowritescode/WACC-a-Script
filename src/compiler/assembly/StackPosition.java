package assembly;

public class StackPosition {
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