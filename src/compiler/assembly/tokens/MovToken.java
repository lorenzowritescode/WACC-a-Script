package assembly.tokens;

import assembly.InstrToken;
import assembly.Register;


public abstract class MovToken extends InstrToken {
	protected Register dest;
	protected Register regSource;
	protected String immSource;
	protected String condition = "";
	
}
