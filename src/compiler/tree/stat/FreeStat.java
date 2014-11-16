package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.IdentNode;
import tree.type.ArrayTypeNode;
import tree.type.PairTypeNode;
import WACCExceptions.InvalidTypeException;

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}
	
	/* (non-Javadoc)
	 * @see tree.stat.StatNode#check(symboltable.SymbolTable, org.antlr.v4.runtime.ParserRuleContext)
	 * Checks to see if expr is an identifier, and if it is,
	 * checks that if that the identifier is in symbol table, it is a pair or an array type
	 */
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (en instanceof IdentNode) {
			IdentNode identN = (IdentNode) en;
			String ident = identN.getIdent();
			if (st.containsRecursive(ident)) {
				if (!(st.get(ident).getType() instanceof ArrayTypeNode 
					|| st.get(ident).getType() instanceof PairTypeNode)) {
					new InvalidTypeException("Can only free an Array or a Pair", ctx);
					return false;
				}
			}
			return true;
		}
		new InvalidTypeException("'Free' must be passed an identifier to a variable", ctx);
		return false;
	}

}
