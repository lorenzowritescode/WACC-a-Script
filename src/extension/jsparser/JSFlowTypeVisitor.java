package jsparser;

import java.util.ArrayList;
import java.util.List;

import jsparser.build.JSTypeParser.ArglistContext;
import jsparser.build.JSTypeParser.ArgumentContext;
import jsparser.build.JSTypeParser.ArrayContext;
import jsparser.build.JSTypeParser.Base_typeContext;
import jsparser.build.JSTypeParser.FunctionContext;
import jsparser.build.JSTypeParser.LibraryContext;
import jsparser.build.JSTypeParser.PairContext;
import jsparser.build.JSTypeParser.TypeContext;
import jsparser.build.JSTypeParser.TypeDefContext;
import jsparser.build.JSTypeParserBaseVisitor;

public class JSFlowTypeVisitor extends JSTypeParserBaseVisitor<Object> {

	@Override
	public Object visitArgument(ArgumentContext ctx) {
		LibType type = (LibType) visit(ctx.typeDef());
		return new LibArg(type);
	}

	@Override
	public List<LibFunc> visitLibrary(LibraryContext ctx) {
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
		LibBaseType returnType = (LibBaseType) visit(ctx.typeDef());
		LibArgList args = (LibArgList) visit(ctx.arglist());
		
		return new LibFunc(fName, returnType, args);
	}

	@Override
	public Object visitTypeDef(TypeDefContext ctx) {
		return visit(ctx.type());
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
	public Object visitBase_type(Base_typeContext ctx) {
		return LibBaseType.parse(ctx.getText());
	}

	@Override
	public Object visitArray(ArrayContext ctx) {
		LibType inner = (LibType) visit(ctx.type());
		return new LibArray(inner);
	}

	@Override
	public Object visitPair(PairContext ctx) {
		LibType fst = (LibType) visit(ctx.type(0));
		LibType snd = (LibType) visit(ctx.type(1));
		return new LibPair(fst, snd);
	}

}
