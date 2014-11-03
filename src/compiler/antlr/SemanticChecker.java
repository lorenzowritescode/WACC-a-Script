

import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.tree.ParseTree;

import WACCParser.Pair_literContext;


public class SemanticChecker extends WACCParserBaseVisitor<ParseTree> {
	
	@Override
	public ParseTree visitPair_liter(Pair_literContext ctx) {
		// TODO Auto-generated method stub
		return super.visitPair_liter(ctx);
	}

	@Override
	public ParseTree visitStr_liter(Str_literContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStr_liter(ctx);
	}

	@Override
	public ParseTree visitBool_liter(Bool_literContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBool_liter(ctx);
	}

	@Override
	public ParseTree visitArray_liter(Array_literContext ctx) {
		// TODO Auto-generated method stub
		return super.visitArray_liter(ctx);
	}

	@Override
	public ParseTree visitInt_liter(Int_literContext ctx) {
		// TODO Auto-generated method stub
		return super.visitInt_liter(ctx);
	}

	private ParseTree waccTree;
	private Map<String, varType> varMap = new OrderedHashMap<String, varType>();
	
	public SemanticChecker(ParseTree t){
		this.waccTree = t;
	}
	
	
	

}
