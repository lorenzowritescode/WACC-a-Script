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
		ParseTree tree = parser.prog();
		
		SemanticChecker semantic = new SemanticChecker(tree);
		semantic.init();
//		WACCCompiler compiler = new WACCCompiler(semantic);
//		compiler.init();
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
