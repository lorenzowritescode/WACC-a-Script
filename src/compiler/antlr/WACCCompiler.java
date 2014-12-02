package antlr;

import tree.WACCTree;
import assembly.Register;
import assembly.TokenCollector;
import assembly.TokenSequence;

public class WACCCompiler {
	
	private WACCTree tree;
	private TokenSequence progSequence;
	
	public WACCCompiler(WACCTree t){
		this.tree = t;
	}
	
	public void init() {
		TokenSequence bodySequence = tree.toAssembly(new Register());
		TokenCollector tc = new TokenCollector(bodySequence);
		progSequence = tc.collect();
	}
	
	@Override
	public String toString() {
		return progSequence.toString();
	}

}
