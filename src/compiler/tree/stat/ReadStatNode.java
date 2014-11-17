package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;
import WACCExceptions.IncompatibleTypesException;
import assignments.AssignLhsNode;

public class ReadStatNode extends StatNode {

	private AssignLhsNode lhs;
	
	public ReadStatNode(AssignLhsNode lhs) {
		this.lhs = lhs;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx) {
		WACCType type = lhs.getType();
		if (type == WACCType.INT || type == WACCType.CHAR || type == WACCType.STRING) {
			return true;
		}
		throw new IncompatibleTypesException("Variable cannot be read into.", ctx);
	}
}
