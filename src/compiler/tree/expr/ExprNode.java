package tree.expr;

import tree.assignments.Assignable;
import assembly.Register;
import assembly.StackAllocator;
import assembly.TokenSequence;

public abstract class ExprNode extends Assignable  {
	
	/*
	 * The implementation of this class is left empty and abstract, 
	 * and will be used as a super class for concrete expr classes.
	 */
	public abstract int weight();

	/**
	 * @param register the next free register
	 * @return Returns a token sequence corresponding to the instructions
	 * required to print the specific expression from which this method's
	 * called.
	 */
	public abstract TokenSequence printAssembly(Register register);
}
