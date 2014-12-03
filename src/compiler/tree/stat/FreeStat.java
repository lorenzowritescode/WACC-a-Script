package tree.stat;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.VarNode;
import tree.type.ArrayType;
import tree.type.PairType;
import WACCExceptions.InvalidTypeException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.FreeArrayToken;
import assembly.tokens.FreePairToken;
import assembly.tokens.LoadAddressToken;
import assembly.tokens.MovRegToken;

/**
 * Class to represent free statements used to free array or pair variables
 * Rule: 'free' expr
 *
 */

public class FreeStat extends StatNode {
	
	private ExprNode en;
	
	public FreeStat(ExprNode en) {
		this.en = en;
	}
	
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		if (en instanceof VarNode) {
			VarNode identN = (VarNode) en;
			String ident = identN.getIdent();
			if (st.containsRecursive(ident)) {
				if (!(st.get(ident).getType() instanceof ArrayType
					|| st.get(ident).getType() instanceof PairType)) {
					new InvalidTypeException("Can only free an Array or a Pair", ctx);
					return false;
				}
			}
			return true;
		}
		new InvalidTypeException("'Free' must be passed an identifier to a variable", ctx);
		return false;
	}
	
	public TokenSequence toAssembly(Register register) {
		TokenSequence freeStat = new TokenSequence();
		if (en.getType() instanceof ArrayType) {
			freeStat.appendAll(new TokenSequence(
				new LoadAddressToken(register, Register.sp),
				new MovRegToken(Register.R0, register),
				new FreeArrayToken()));
		} else {
			freeStat.appendAll(new TokenSequence(
				new LoadAddressToken(register, Register.sp),
				new MovRegToken(Register.R0, register),
				new FreePairToken()));
		}
		return freeStat;
	}

}
