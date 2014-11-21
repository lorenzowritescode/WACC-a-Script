package antlr;

import java.util.List;

import tree.WACCTree;
import assembly.InstrToken;
import assembly.Register;

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
		String assemblyCode = "";
		List<InstrToken> tokens = progInstruction.flatten();
		for (InstrToken t:tokens) {
			assemblyCode += t.toString() + "\n";
		}
		return assemblyCode;
	}

}
