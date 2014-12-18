package assembly;

public class ImmValue {
	
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
