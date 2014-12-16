package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.WACCTree;
import tree.assignments.AssignLhsNode;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
import WACCExceptions.IncompatibleTypesException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.MovRegToken;
import assembly.tokens.ReadToken;

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
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx) {
		WACCType type = lhs.getType();
		if (type == WACCType.INT || type == WACCType.CHAR || type == WACCType.STRING) {
			return true;
		}
		throw new IncompatibleTypesException("Variable cannot be read into.", ctx);
	}
	
	public TokenSequence toAssembly(Register register) {
		AssignLhsNode lhsNode = (AssignLhsNode) lhs;
		return lhsNode.loadAddress(register)
				.append(new MovRegToken(Register.R0, register))
				.append(new ReadToken(lhs.getType()));
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitReadStatNode(this);
	}

	public WACCTree getLhs() {
		return lhs;
	}
}
