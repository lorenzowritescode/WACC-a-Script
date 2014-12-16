package jsparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jsparser.build.JSTypeLexer;
import jsparser.build.JSTypeParser;
import jsparser.build.JSTypeParser.LibraryContext;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class JSLibParser {

	private String fp;
	
	public JSLibParser(String libFilePath) {
		this.fp = libFilePath;
	}
	
	public List<LibFunc> getLibFuncs() {

		InputStream iostream = null;
		try {
			iostream = new FileInputStream(fp);
		} catch (FileNotFoundException e) {
			System.err.println("Could not open file");
			e.printStackTrace();
		}
		
		ANTLRInputStream antrlInput = null;
		try {
			antrlInput = new ANTLRInputStream(iostream);
		} catch (IOException e) {
			System.err.println("Error converting to AntlrInputStream");
			e.printStackTrace();
		}
		
		JSTypeLexer lexer = new JSTypeLexer(antrlInput);

		// Tokenise the input stream
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Build the tokens AST
		JSTypeParser parser = new JSTypeParser(tokens);
		LibraryContext libraryFunctions = parser.library();
		
		JSFlowTypeVisitor visitor = new JSFlowTypeVisitor();
		List<LibFunc> fs = visitor.visitLibrary(libraryFunctions);
		
		return fs;
	}
}
