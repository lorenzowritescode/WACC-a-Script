package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.assignments.AssignLhsNode;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import assembly.Register;
import assembly.TokenSequence;

/**
 * Class to represent read statements  
 * Rule: 'read' assign-lhs
 */

public class ReadStatNode extends StatNode {

	private WACCTree lhs;
	
	public ReadStatNode(AssignLhsNode lhs) {
		this.lhs = (WACCTree) lhs;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx) {
		WACCType type = lhs.getType();
		if (type == WACCType.INT || type == WACCType.CHAR || type == WACCType.STRING) {
			return true;
		}
		throw new IncompatibleTypesException("Variable cannot be read into.", ctx);
	}
	
	public TokenSequence toAssembly(Register register) {
		TokenSequence exprSeq = lhs.toAssembly(register);
		exprSeq.appendAll( new TokenSequence(
				// TODO: This depends upon the type apparently
				));
		return exprSeq;
	}
}
