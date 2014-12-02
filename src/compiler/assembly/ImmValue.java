package assembly;

public class ImmValue {
	
	private int value;
	
	public void setNumber(int i) {
		this.value = i;
	}
	
	public static final ImmValue zero = new ImmValue() {
		@Override
		public String toString() {
			return "#0";
		}
	};
	
	public static final ImmValue one = new ImmValue() {
		@Override
		public String toString() {
			return "#1";
		}
	};

}
