package tree.assignments;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import symboltable.SymbolTable;
import tree.expr.ExprNode;
import tree.expr.VarNode;
import tree.type.ArrayType;
import tree.type.WACCType;
import visitor.WACCTreeVisitor;
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
	public boolean check(SymbolTable funcSt, SymbolTable st, ParserRuleContext ctx ) {
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
		WACCType type = arrayType;
		for (int i = 0; i < locations.size(); i++) {
			type = ((ArrayType) type).getBaseType();
		}
		return type;
	}
	
	@Override
	public TokenSequence toStoreAssembly(Register dest) {
		return arrayElemCommonAssembly(dest.getNext())
				.append(arrayType.getBaseType().storeAssembly(dest.getNext(), dest));
	}
	
	
	public TokenSequence toAssembly(Register dest) {
		TokenSequence out = new TokenSequence();
		TokenSequence arrayAccess = arrayElemCommonAssembly(dest);
		LoadAddressToken loadResult = arrayType.getBaseType().loadAssembly(dest, dest);
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
					new AddImmToken(dest, dest, Integer.toString(4))));
			
			if(arrayType.getBaseType().getVarSize() == WACCType.INT.getVarSize()) {
				exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
			} else {
				exprSeq.append(new AddToken(dest, dest, dest.getNext()));
			}
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

	@Override
	public TokenSequence loadAddress(Register dest) {
		TokenSequence seq = var.toAssembly(dest);
		TokenSequence arrayIndex = this.locations.get(0).toAssembly(dest.getNext());
		seq.appendAll(arrayIndex);
		seq.appendAll(
				new LoadAddressToken(dest, dest),
				new AddToken(dest, dest, dest.getNext()));
		return seq;
	}

	@Override
	public <T> T accept(WACCTreeVisitor<T> visitor) {
		return visitor.visitArrayElemNode(this);
	}

	public VarNode getVar() {
		return var;
	}

	public ArrayList<ExprNode> getLocations() {
		return locations;
	}
}
