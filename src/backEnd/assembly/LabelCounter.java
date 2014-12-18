package assembly;

public class LabelCounter {
	
	private int i = 0;
	
	public int getLabel() {
		int output = i;
		i++;
		return output;
	}
	
	public static final LabelCounter counter = new LabelCounter() {
	};
	
}
