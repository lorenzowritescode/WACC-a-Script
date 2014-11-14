package antlr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class WACCCompiler {

	public static void main(String[] args) throws IOException {
		// Parse the flags given in the command line arguments
		CommandLine cmd = parseFlags(args);
		
		// We set the logging options as appropriate
		SemanticChecker.dbh.setOptions(cmd);
		
		// Get WACC source File
		String waccFilePath = cmd.getOptionValue("f");
		InputStream waccInput = getInputStream(waccFilePath);
		
		// Create ANTLR Input stream
		ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);
		WACCLexer lexer = new WACCLexer(antrlInput);
		
		// Tokenise the input stream
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		// Build the tokens AST
		ParseTree tree = getParseTree(tokens);
		
		// Check for semantic errors
		SemanticChecker sc = checkSemanticIntegrity(tree);
		
		// Check that the Error Listener has not recorded any exceptions
		if ( sc.terminate() ) {
			exitSemanticFailure();
		}
//		WACCCompiler compiler = new WACCCompiler(semantic);
//		compiler.init();
	}
	
	/**
	 * @param tree
	 * 		An ANTLR.ParseTree that represents a WACC program
	 * @return
	 * 		The Semantic Checker object
	 */
	private static SemanticChecker checkSemanticIntegrity(ParseTree tree) {
		SemanticChecker semantic = new SemanticChecker(tree);
		try {
			semantic.init();
		} catch (Exception e) {
			e.printStackTrace();
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
		// Create Parser from Tokens
		WACCParser parser = new WACCParser(tokens);
		// Set tree to null for the moment
		ParseTree tree = null;
		try {
			tree = parser.prog();
		} catch (Exception e) {
			e.printStackTrace();
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
		// add v option
		options.addOption("v", false, "verbose");
		options.addOption("d", false, "debug mode");
		options.addOption("f", true, "source file");
		
		CommandLineParser flagsParser = new PosixParser();
		CommandLine cmd = null;
		try {
			 cmd = flagsParser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("There were problems parsing the flags");
			e.printStackTrace();
		}
		return cmd;
	}
	

	private static void exitSyntaxError() {
		System.exit(100);
	}

	private static void exitSemanticFailure() {
		System.exit(200);
	}
	
	/*
	 * Compiler class, used to generate assembly code from a SemanticChecker object.
	 */
	private SemanticChecker semanticChecker;
	
	public WACCCompiler(SemanticChecker s){
		this.semanticChecker = s;
	}
	
	private void init() {
		//TODO: implement init
		return;
	}

}
