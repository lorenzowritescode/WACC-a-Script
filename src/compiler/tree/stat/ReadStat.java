package tree.stat;

import assignments.AssignLhsNode;

public class ReadStat extends StatNode {

	private AssignLhsNode assignLhs;

	public ReadStat(AssignLhsNode assignLhs) {
		this.assignLhs = assignLhs;
	}

}
