package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.PairTypeNode;
import tree.type.WACCType;

public class PairElemNode extends Assignable implements AssignLhsNode {
	
	private ExprNode expr;
	private String pairName;
	private WACCType type;
	
	//Expr here should be of type 'pairType'
	public PairElemNode(ExprNode expr, String pairName, WACCType type) {
		this.expr = expr;
		this.pairName = pairName;
		this.type = type;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (!(expr.getType() instanceof PairTypeNode)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getIdent() {
		return pairName;
	}
	
	@Override
	public WACCType getType() {
		return type;
	}

	@Override
	public boolean checkPreDef(SymbolTable st, String identName, ParserRuleContext ctx) {
		return true;
	}

}
