package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;

public class WACCException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8463718756840085322L;
	
	private ParserRuleContext ctx;

	public WACCException(String exceptionMessage, ParserRuleContext ctx){
		super(exceptionMessage);
		this.ctx = ctx;
	}

	@Override
	public String toString() {
		String s = "";
		//print line number and column
		int line = ctx.start.getLine();
		int pos  = ctx.start.getCharPositionInLine();
		s += "Error at line " + line + ":" + pos + " : ";
		//print our error message
		s += this.getMessage() +"\n";
		//print source code
		s += "Source Code: \n" + ctx.getText() + "\n\n";
		return s;
	}
}
