package tree.stat;

import assembly.Register;
import assembly.TokenSequence;

public class SkipStatNode extends StatNode {

	//Skip statement does nothing
	public SkipStatNode() {
	}
	
	public TokenSequence toAssembly(Register register) {
		return new TokenSequence();
	}

}
