package WACCExceptions;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import antlr.WACCParser.StatContext;

@SuppressWarnings("serial")
public class WACCException extends RuntimeException {
	
	public static final ErrorListener ERROR_LISTENER = new ErrorListener();
	
	private ParserRuleContext ctx;
	private String message;

	public WACCException(String exceptionMessage, ParserRuleContext ctx){
		this.message = exceptionMessage;
		this.ctx = ctx;
		ERROR_LISTENER.record(this);
	}
	
	public WACCException(String message) {
		this.message = message;
		this.ctx = null;
	}
	
	public WACCException() {
		this.message = "Semantic Error found.";
		this.ctx = null;
	}

	@Override
	public String toString() {
		String errorString = this.getClass().getName() + ": ";
		//print our error message
		errorString += this.message +"\n";
		
		if (ctx == null) {
			errorString += "[INFO] Position information is not available for this error.";
			return errorString;
		}
		
		ErrorPosition epos = new ErrorPosition(ctx);
		//print line number and column
		int line = epos.getLine();
		int pos  = epos.getCharPos();
		errorString += "Error at " + line + ":" + pos + " : ";
		//print source code
		errorString += "Source Code: " + epos.getText() + "\n\n";
		return errorString;
	}

	private class ErrorPosition {
		private ParserRuleContext ctx;
		private Token firstValidToken;
		private ParserRuleContext firstStatParent;

		public ErrorPosition(ParserRuleContext ctx) {
			this.ctx = ctx;
			this.firstValidToken = findToken(ctx);
			try {
				this.firstStatParent = findParentRule(ctx);
			} catch (Exception e) {
				this.firstStatParent = ctx;
			}
		}

		public int getLine() {
			return firstValidToken.getLine();
		}
		
		public int getCharPos() {
			return firstValidToken.getCharPositionInLine();
		}
		
		public String getText() {
			return firstStatParent.getText();
		}
		
		private Token findToken(ParserRuleContext rule) {
			if (rule == null)
				throw new RuntimeException("the rule provided to the exception was null");
			if (rule.start == null)
				return findToken((ParserRuleContext) ctx.getChild(0));
			
			return rule.start;
		}
		
		private ParserRuleContext findParentRule(ParserRuleContext ctx) throws Exception {
			int MAX_DEPTH = 15;
			ParserRuleContext current = ctx;
			int i = 0;
			while(!(ctx instanceof StatContext) && i++ < MAX_DEPTH) {
				current = ctx.getParent();
				if (current == null)
					throw new Exception("Could not find a Stat parent");
			}
			return current;
		}
	}
}
