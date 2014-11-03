import org.antlr.v4.runtime.tree.ParseTree;


public class SemanticChecker {
	
	private ParseTree t;
	private WACCVisitor visitor;
	
	public SemanticChecker(ParseTree t) {
		this.t = t;
	}

}
