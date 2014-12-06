package tree.assignments;

import assembly.Register;
import assembly.TokenSequence;


public interface AssignLhsNode {
	TokenSequence toStoreAssembly(Register dest);
	TokenSequence loadAddress(Register dest);
}
