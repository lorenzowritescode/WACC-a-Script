package JSTree;

public class JSInt implements JSTree {
	
	private int val;
	
	public JSInt(int val) {
		this.val = val;
	}

	@Override
	public String toCode() {
		return String.valueOf(val);
	}

}
