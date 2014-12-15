package jsparser;

import java.util.ArrayList;
import java.util.List;

import jsparser.build.JSTypeParser.ArglistContext;
import jsparser.build.JSTypeParser.ArgumentContext;
import jsparser.build.JSTypeParser.FunctionContext;
import jsparser.build.JSTypeParser.LibraryContext;
import jsparser.build.JSTypeParser.TypeContext;
import jsparser.build.JSTypeParser.TypeDefContext;
import jsparser.build.JSTypeParserBaseVisitor;

public class JSFlowTypeVisitor extends JSTypeParserBaseVisitor<Object> {

	@Override
	public Object visitArgument(ArgumentContext ctx) {
		return new LibArg(LibType.parse(ctx.getText()));
	}

	@Override
	public Object visitLibrary(LibraryContext ctx) {
		List<LibFunc> library = new ArrayList<>();
		for (FunctionContext f:ctx.function()) {
			LibFunc lf = (LibFunc) visit(f);
			library.add(lf);
		}
		return library;
	}

	@Override
	public Object visitFunction(FunctionContext ctx) {
		String fName = ctx.IDENTITY().getText();
		LibType returnType = (LibType) visit(ctx.typeDef());
		LibArgList args = (LibArgList) visit(ctx.arglist());
		
		return new LibFunc(fName, returnType, args);
	}

	@Override
	public Object visitTypeDef(TypeDefContext ctx) {
		return LibType.parse(ctx.type().getText());
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


	@Override
	public Object visitArglist(ArglistContext ctx) {
		// TODO Auto-generated method stub
		return super.visitArglist(ctx);
	}

}
