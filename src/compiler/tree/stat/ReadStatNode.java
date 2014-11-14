package tree.stat;

import assignments.AssignLhsNode;

public class ReadStatNode extends StatNode {

	private AssignLhsNode lhs;
	
	public ReadStatNode(AssignLhsNode lhs) {
		this.lhs = lhs;
	}
}
