package antlr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class WACCCompiler {

	public static void main(String[] args) throws IOException {
		InputStream waccInput;
		if(args.length > 0)
			waccInput = new FileInputStream(args[0]);
		else
			waccInput = System.in;
		ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);
		WACCLexer lexer = new WACCLexer(antrlInput);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		WACCParser parser = new WACCParser(tokens);
		
		try {
			ParseTree tree = parser.prog();
			SemanticChecker semantic = new SemanticChecker(tree);
			// Comment out next line for debugging
			// SemanticChecker.dbh.turnOff();
			semantic.init();
		} catch (ClassCastException e) {
			System.err.println("Illformed instruction: ");
			e.printStackTrace();
			exitSemanticFailure();
		} catch (Exception e) {
			e.printStackTrace();
			exitSyntaxError();
		}
		
		if (SemanticChecker.ERROR_LISTENER.errorCount() > 0) {
			System.err.println(SemanticChecker.ERROR_LISTENER.printErrors());
			exitSemanticFailure();
		}
//		WACCCompiler compiler = new WACCCompiler(semantic);
//		compiler.init();
	}

	private static void exitSyntaxError() {
		System.exit(100);
	}

	private static void exitSemanticFailure() {
		System.exit(200);
	}

	private SemanticChecker semanticChecker;
	
	public WACCCompiler(SemanticChecker s){
		this.semanticChecker = s;
	}
	
	private void init() {
		//TODO: implement init
		return;
	}

}
