package assignments;


public class CallStatNode extends AssignRhsNode {
	
	private String ident;
	private ArgListNode args;
	
	public CallStatNode(String ident, ArgListNode args) {
		this.ident = ident;
		this.args = args;
	}
	
}
