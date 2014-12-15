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
	public static void main(String[] args) {
		// Get WACC source File
		String libFilePath = args[0];
		
		InputStream iostream = null;
		try {
			iostream = new FileInputStream(libFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ANTLRInputStream antrlInput = null;
		try {
			antrlInput = new ANTLRInputStream(iostream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		
		for(LibFunc f:fs) {
			System.out.println(f.toString());
		}
	}
}
