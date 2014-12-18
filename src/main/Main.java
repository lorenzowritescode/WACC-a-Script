package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import jsparser.JSLibParser;
import jsparser.LibFunc;
import jsparser.LibraryLoader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import antlr.WACCLexer;
import antlr.WACCParser;
import compiler.WACCCompiler;
import semanticChecking.SemanticChecker;
import symboltable.SymbolTable;
import tree.WACCTree;
import JSTree.WACCTreeToJsTree;
import WACCExceptions.IntOverflowException;
import WACCExceptions.UnresolvedExpectationException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// Parse the flags given in the command line arguments
		CommandLine cmd = parseFlags(args);
		
		// We set the logging options as appropriate
		SemanticChecker.dbh.setOptions(cmd);
		
		// Get WACC source File
		String waccFilePath = cmd.getOptionValue("f");
		InputStream waccInput = getInputStream(waccFilePath);
		
		WACCLexer lexer = getLexer(waccInput);
		
		// Tokenise the input stream
		CommonTokenStream tokens = tokenise(lexer);
		
		// Build the tokens AST
		ParseTree tree = getParseTree(tokens);
		
		
		
		// Get external JS source file
		SymbolTable libTable = new SymbolTable();
		LibraryLoader ll = new LibraryLoader(libTable);
		
		// funcDependancies stores the function name, and the required file name for that function
		// (e.g. <Function Name, Required Package name>)
		HashMap<String, String> funcDependancies = new HashMap<>();
		
		if(cmd.hasOption("j") && cmd.hasOption("l")) {
			String[] libs = cmd.getOptionValue("l").split(":");
			for (String lib : libs) {
				JSLibParser libParser = new JSLibParser(lib);
				List<LibFunc> funcs = libParser.getLibFuncs();
				ll.loadIntoSymbolTable(funcs);
				funcDependancies.putAll(ll.findDependancies(lib, funcs));
			}
			

		}
		
		// Check for semantic errors
		SemanticChecker sc = checkSemanticIntegrity(tree, libTable);
		
		// Check that the Error Listener has not recorded any exceptions
		if ( !sc.terminate() ) {
			exitSemanticFailure();
		}
		
		// Extract the path from the waccFilePath string
		if (waccFilePath == null) {
			waccFilePath = "temp.wacc";
		}
			
		Path p = Paths.get(waccFilePath);
		 

		// String that will contain the final program
		String outputString = "";
		// String that contains the extension for the output file
		String extension = "";
		// WACCTree that contains the program
		WACCTree wt = sc.getProgTree();
		
		// Get the filename and replace the extension
		if(cmd.hasOption('j')) {
			outputString = compileToJavaScript(wt, funcDependancies);
			extension = ".js";
		} else {
			outputString = compileToArmAssembly(wt);
			extension = ".s";
		}
		
		String outputFileName = p.getFileName().toString().replace(".wacc", extension);
		
		// If the s flag is on we want to print to stdout;
		if (cmd.hasOption("s")) {
			System.out.println(outputString);
		} else {
			writeToFile(outputString, outputFileName);
		}
	}



	private static String compileToJavaScript(WACCTree wt, HashMap<String, String> funcDeps) {
		WACCTreeToJsTree converter = new WACCTreeToJsTree(wt, funcDeps);
		return converter.init();	
	}

	private static String compileToArmAssembly(WACCTree wt) {
		WACCCompiler compiler = new WACCCompiler(wt);
		compiler.init();
		String compilerOutput = compiler.toString();
		
		return compilerOutput;
	}

	/** Utility method to extract the assembly filename and write to file
	 * @param resultString A String containing the assembly output
	 * @param fileName The String containing the path of the source file. It works with just the filename.
	 */
	private static void writeToFile(String resultString, String fileName) {
		// Write to file
		try {
			File file = new File(fileName);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(resultString);
			output.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * @param waccInput
	 * 		The InputStream containing the WACC program
	 * @return
	 * 		A WACCLexer
	 * @throws IOException
	 */
	
	private static WACCLexer getLexer(InputStream waccInput) throws IOException {
		// Create ANTLR Input stream
		WACCLexer lexer = null;
		try {
			ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);
			lexer = new WACCLexer(antrlInput);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			System.err.println(e.toString());
			exitSyntaxError();
		}
		return lexer;
	}
	
	private static CommonTokenStream tokenise(WACCLexer lexer) {
		CommonTokenStream tokens = null;
		try {
			tokens = new CommonTokenStream(lexer);
		}
		catch (Exception e) {
			System.err.println(e.toString());
			exitSemanticFailure();
		}
		return tokens;
	}

	/**
	 * @param tree
	 * 		An ANTLR.ParseTree that represents a WACC program
	 * @return
	 * 		The Semantic Checker object
	 */
	private static SemanticChecker checkSemanticIntegrity(ParseTree tree, SymbolTable libTable) {
		SemanticChecker semantic = new SemanticChecker(tree, libTable);
		try {
			semantic.init();
		} catch (UnresolvedExpectationException e) {
			System.err.println(e.toString());
			exitSyntaxError();
		} catch (IntOverflowException e) {
			System.err.println(e.toString());
			exitSyntaxError();
		} catch (Exception e) {
			System.err.println(e.toString());
			exitSemanticFailure();
		}
		return semantic;
	}

	/**
	 * @param tokens
	 * 		The tokens of the WACC program
	 * @return
	 * 		An ANTLR AST representing the program
	 */
	private static ParseTree getParseTree(CommonTokenStream tokens) {
		WACCParser parser = null;
		ParseTree tree = null;
		try {
			// Create Parser from Tokens
			parser = new WACCParser(tokens);
			// Set tree to null for the moment
			tree = parser.prog();
		} catch (Exception e) {
			System.err.println(e.toString());
			exitSyntaxError();
		}
		
		if(parser.getNumberOfSyntaxErrors() > 0) {
			exitSyntaxError();
		}
		return tree;
	}

	/**
	 * @param waccFilePath
	 * 		The path of the File to be compiled
	 * @return
	 * 		The InputStream 
	 * @throws FileNotFoundException
	 * 		If the filePath provided is not found.
	 */
	private static InputStream getInputStream(String waccFilePath)
			throws FileNotFoundException {
		// Initialise InputStream to the file or System.in if null.
		InputStream waccInput;
		if(waccFilePath != null)
			waccInput = new FileInputStream(waccFilePath);
		else
			waccInput = System.in;
		return waccInput;
	}

	/**
	 * @param args
	 * 		The arguments array passed to the command line
	 * @return
	 * 		A CommandLine object that contains all the flag values.
	 */
	private static CommandLine parseFlags(String[] args) {
		// create Options object
		Options options = new Options();
		// The first argument is the flag character, the second argument 
		// is true if the flag requires and argument. The third flag describes the behavior of the flag
		options.addOption("v", false, "verbose");
		options.addOption("d", false, "debug mode");
		options.addOption("f", true, "source file");
		options.addOption("s", false, "force printing assembly to std-out");
		options.addOption("j", false, "Compile into Javascript");
		options.addOption("l", true, "Include external javaScript libraries (e.g. \"core.js:math.js\"");
		
		CommandLineParser flagsParser = new PosixParser();
		CommandLine cmd = null;
		try {
			 cmd = flagsParser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("There were problems parsing the flags");
			System.err.println(e.toString());
		}
		return cmd;
	}
	

	private static void exitSyntaxError() {
		System.exit(100);
	}

	private static void exitSemanticFailure() {
		System.exit(200);
	}
}
