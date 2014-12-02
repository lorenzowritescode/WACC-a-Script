package tree.stat;

import assembly.Register;
import assembly.TokenSequence;

public class SkipStatNode extends StatNode {

	//Skip statement does nothing
	public SkipStatNode() {
	}
	
	public TokenSequence toAssembly(Register register) {
		//TODO: skip: make sure this doesnt break anything
		return new TokenSequence();
	}

}
