package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.*;
import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.type.ArrayType;
import tree.type.WACCType;

public class ArrayLiterNode extends Assignable {

	private ArrayList<ExprNode> elems;
	private WACCType baseType;
	
	public ArrayLiterNode(ArrayList<ExprNode> elems) {
		this.elems = elems;
		if (elems.size() > 0) {
			baseType = elems.get(0).getType();
		} else {
			baseType = WACCType.NULL;
		}
	}
	
	@Override
	public boolean check(SymbolTable st, ParserRuleContext ctx) {
		//Iterates through array  and checks all elements are of the same type (i.e. baseType)
		for(int i = 0; i < elems.size(); i++) {
			if (!elems.get(i).getType().isCompatible(baseType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public WACCType getType() {
		//Base Type might be null if empty array
		return new ArrayType(baseType);
	}

	@Override
	public TokenSequence toAssembly(Register dest) {
		int arrayLength = elems.size();
		
		//allocate memory for the arraySize and its elements(addresses)
		TokenSequence allocateArray = mallocSequence(arrayLength, baseType.getVarSize());
		MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
		allocateArray.append(movReg1);
		
		//iterates through the list of array elems, and produces their assembly code
		int count = 1;
		for (ExprNode node : elems) {
			TokenSequence exprSeq = node.toAssembly(dest.getNext());
			StoreToken storeElem = new StoreToken(dest, dest.getNext(), (count*this.getType().getVarSize()));
			allocateArray
			.appendAll(exprSeq)
			.append(storeElem);
			
			count++;
		}
		
		//store the arraySize
		LoadToken loadSize = new LoadToken(dest.getNext(), Integer.toString(arrayLength));
		StoreToken storeSize = new StoreToken(dest, dest.getNext());
		
		allocateArray
		.append(loadSize)
		.append(storeSize);
		
		return allocateArray;
	}
	
}
