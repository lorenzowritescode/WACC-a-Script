package assignments;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.type.WACCType;

public interface AssignLhsNode {

	public boolean check( SymbolTable st, ParserRuleContext ctx );

	public WACCType getType();
	
	public String getIdent();

}
