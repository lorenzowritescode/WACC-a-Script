package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.*;
import assembly.tokens.*;
import symboltable.SymbolTable;
import tree.expr.*;
import tree.type.ArrayType;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

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
			TokenSequence loadPos = expr.toAssembly(dest.getNext());
			LoadAddressToken loadArrAdd = new LoadAddressToken(dest, dest);
			MovRegToken movPosToR0 = new MovRegToken(Register.R0, dest.getNext());
			MovRegToken movAddToR1 = new MovRegToken(Register.R1, dest);
			BranchLinkToken branchToCheck = new BranchLinkToken("p_check_array_bounds");
			AddImmToken skipLength = new AddImmToken(dest, dest, Integer.toString(4));
			AddToken accessArrayElem = new AddToken(dest, dest, dest.getNext(), "LSL #2");
			
			out
			.appendAll(loadPos)
			.append(loadArrAdd)
			.append(movPosToR0)
			.append(movAddToR1)
			.append(branchToCheck)
			.append(skipLength)
			.append(accessArrayElem);
		}
		
		return out;
	}
	
	
	public String getIdent() {
		return var.getIdent();
	}

	@Override
	public int weight() {
		//TODO: perhaps has to do with the exprs?
		return 1;
	}

	@Override
	public TokenSequence printAssembly(Register register) {
		// TODO Auto-generated method stub
		return null;
	}

}
