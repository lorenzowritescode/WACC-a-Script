package antlr;

import java.util.List;

import tree.WACCTree;
import assembly.InstrToken;
import assembly.Register;
import assembly.TokenCollector;

public class WACCCompiler {
	
	private WACCTree tree;
	private InstrToken progInstruction;
	
	public WACCCompiler(WACCTree t){
		this.tree = t;
	}
	
	public void init() {
		this.progInstruction = tree.toAssembly(new Register());
	}
	
	@Override
	public String toString() {
		TokenCollector tc = new TokenCollector(progInstruction);
		List<InstrToken> program = tc.collect();
		String programString = "";
		
		for (InstrToken t:program) {
			programString += t.toString() + "\n";
		}
		
		return programString;
	}

}
