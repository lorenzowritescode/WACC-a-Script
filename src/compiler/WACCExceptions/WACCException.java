package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class WACCException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8463718756840085322L;
	
	private ParserRuleContext ctx;

	public WACCException(String exceptionMessage, ParserRuleContext ctx){
		super(exceptionMessage);
		if (ctx == null) {
			throw new RuntimeException("WHAT THE ACTUAL FUCK");
		}
		this.ctx = ctx;
	}

	@Override
	public String toString() {
		String s = "";
		ErrorPosition epos = new ErrorPosition(ctx);
		//print line number and column
		int line = epos.getLine();
		int pos  = epos.getCharPos();
		s += "Error at " + line + ":" + pos + " : ";
		//print our error message
		s += this.getMessage() +"\n";
		//print source code
		s += "Source Code: " + epos.getText() + "\n\n";
		return s;
	}
	
	private class ErrorPosition {
		private ParserRuleContext ctx;
		private Token firstValidToken;

		public ErrorPosition(ParserRuleContext ctx) {
			this.ctx = ctx;
			this.firstValidToken = findToken(ctx);
		}

		public int getLine() {
			return firstValidToken.getLine();
		}
		
		public int getCharPos() {
			return firstValidToken.getCharPositionInLine();
		}
		
		public String getText() {
			return firstValidToken.getText();
		}
		
		private Token findToken(ParserRuleContext rule) {
			if (rule == null)
				throw new RuntimeException("BLAHHHHHH");
			if (rule.start == null)
				return findToken((ParserRuleContext) ctx.getChild(0));
			
			return rule.start;
		}
	}
}
