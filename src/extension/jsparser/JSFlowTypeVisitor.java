package jsparser;


import java.util.List;

import jsparser.build.JSTypeParser.ArglistContext;
import jsparser.build.JSTypeParser.ArgumentContext;
import jsparser.build.JSTypeParser.FunctionContext;
import jsparser.build.JSTypeParser.LibraryContext;
import jsparser.build.JSTypeParser.TypeContext;
import jsparser.build.JSTypeParserBaseVisitor;

public class JSFlowTypeVisitor extends JSTypeParserBaseVisitor<Object> {

	@Override
	public Object visitArgument(ArgumentContext ctx) {
		return new LibArg(LibType.parse(ctx.getText()));
	}

	@Override
	public Object visitLibrary(LibraryContext ctx) {
		// TODO Auto-generated method stub
		return super.visitLibrary(ctx);
	}

	@Override
	public Object visitFunction(FunctionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunction(ctx);
	}

	@Override
	public Object visitArglist(ArglistContext ctx) {
		List<ArgumentContext> args = ctx.argument();
		LibArgList argList = new LibArgList();
		for(ArgumentContext ac:args) {
			argList.add((LibArg)visit(ac));
		}
		return argList;
	}

}
