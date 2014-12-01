package assembly;

public class StackPosition {
	private int position;
	
	public StackPosition(int i) {
		this.position = i;
	}
	
	public TokenSequence toAssembly(final Register r) {
		final String posString = this.toString();
		return new TokenSequence().append(new InstrToken() {
			@Override
			public String toString() {
				return "LDR " + r.toString() + ", " + posString;
			}
		});
	}
	
	@Override
	public String toString() {
		return position == 0 ? "[sp]" 
				: "[sp, #" + position + "]";
	}
	
	public int getStackIndex() {
		return position;
	}
}