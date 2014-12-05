package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.VarNode;
import tree.type.ArrayType;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.AddImmToken;
import assembly.tokens.AddToken;
import assembly.tokens.CheckArrayBoundsToken;
import assembly.tokens.LoadAddressToken;
import assembly.tokens.MovRegToken;

public class ArrayElemNode extends ExprNode implements AssignLhsNode {
	
	VarNode var;
	ArrayList<ExprNode> locations;
	ArrayType arrayType;

	public ArrayElemNode(ArrayList<ExprNode> locations, VarNode var) {
		this.locations = locations;
		this.arrayType = (ArrayType) var.getType();
		this.var = var;
	}
	
	@Override
	public boolean check( SymbolTable st, ParserRuleContext ctx ) {
		for(ExprNode pos : locations) {
			if(!(pos.getType() == WACCType.INT)) {
				el.record(new InvalidTypeException("Array position can only be found using an Int", ctx));
				return false;
			}
		}
		
		return true;
	}

	@Override
	public WACCType getType() {
		return arrayType.getBaseType();
	}
	
	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		
		return null;
	}
	
	public TokenSequence toAssembly(Register dest) {
		
		TokenSequence out = new TokenSequence();
		TokenSequence arrayAccess = arrayElemCommonAssembly(dest);
		LoadAddressToken loadResult = new LoadAddressToken(dest, dest);
		out
		.appendAll(arrayAccess)
		.append(loadResult);
		
		return out;
	}

	private TokenSequence arrayElemCommonAssembly(Register dest) {
		int posOnStack = var.getPosition().getStackIndex();
		
		TokenSequence out = new TokenSequence();
		
		AddImmToken addTok = new AddImmToken(dest, Register.sp, Integer.toString(posOnStack));
		out.append(addTok);
		
		for (ExprNode expr : locations) {
			TokenSequence exprSeq = expr.toAssembly(dest.getNext());
			
			exprSeq.appendAll( new TokenSequence(
					new LoadAddressToken(dest, dest),
					new MovRegToken(Register.R0, dest.getNext()),
					new MovRegToken(Register.R1, dest),
					new CheckArrayBoundsToken(),
					new AddImmToken(dest, dest, Integer.toString(4)),
					new AddToken(dest, dest, dest.getNext(), "LSL #2")));
			
			out.appendAll(exprSeq);
		}
		
		return out;
	}
	
	
	public String getIdent() {
		return var.getIdent();
	}

	@Override
	public int weight() {
		int max = 0;
		for (ExprNode e:locations) {
			int exprWeight = e.weight();
			max = exprWeight > max ? exprWeight : max; 
		}
		
		return max;
	}

}
