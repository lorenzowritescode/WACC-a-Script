package tree;

import symboltable.SymbolTable;
import WACCExceptions.IncompatibleTypesException;
import WACCExceptions.NotUniqueIdentifierException;
import antlr.WACCParser.Variable_declarationContext;

public class VarDecNode extends WACCTree {
	
	private final WACCType varType;
	private final String identifier;
	private WACCTree rhsTree;
	private Variable_declarationContext ctx;

	public VarDecNode(Variable_declarationContext ctx,
			WACCTree rhsTree) {
		this.ctx = ctx;
		this.identifier = ctx.ident().getText();
		this.varType = WACCType.evalType(ctx.type());
		this.rhsTree = rhsTree;
	}

	@Override
	public boolean check( SymbolTable st ) {		
		// First we check the identifier is unique
		if ( st.containsCurrent(identifier) ) {
			el.record( new NotUniqueIdentifierException(
						"A variable with identifier " + this.identifier + " was already declared", 
						this.ctx)
			);
			return false;
		} 
		
		// We add the current var to the SymbolTable
		st.add(identifier, this);
		
		if ( varType != rhsTree.getType() ) {
			el.record( new IncompatibleTypesException("The types of the rhs and lhs of the variable declaration do not match." , ctx));
			return false;
		}
		return true;
	}

	@Override
	public WACCType getType() {
		return this.varType;
	}

}
