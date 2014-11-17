package symboltable;

import java.util.HashMap;

import assignments.Assignable;
import WACCExceptions.UnresolvedExpectationException;
import tree.WACCTree;
import tree.type.WACCType;

public class SymbolTable {
	
	HashMap<String, WACCTree> dictionary;
	SymbolTable parentTable;
	final boolean isTopSymbolTable;
	private Expectation expectation;
	

	/** Symboltable for a function body.
	 * It expects a return statement of type `expectedReturnType`
	 * @param parentTable
	 * 		The table in the parent scope.
	 * @param expectedReturnType
	 * 		The declared return type of the function.
	 */
	public SymbolTable(SymbolTable parentTable, WACCType expectedReturnType){
		this.parentTable = parentTable;
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = false;
		this.expectation = new Expectation(expectedReturnType);
	}

	/**
	 * The top symbol table. It creates an empty expectation.
	 * Any return statements in the main program body will cause an exception to be recorded.
	 */
	public SymbolTable() {
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = true;
		this.expectation = new Expectation();
		this.parentTable = null;
	}
	

	public boolean containsRecursive(String identifier) {
		if(this.isTopSymbolTable){
			return containsCurrent(identifier);
		}
		return containsCurrent(identifier) || parentTable.containsRecursive(identifier);
	}
	
	public boolean containsCurrent(String identifier) {
		return dictionary.containsKey(identifier);
	}

	public void add(String name, WACCTree node) {
		dictionary.put(name, node);
	}

	public SymbolTable getParent() {
		return this.parentTable;
	}
	
	public WACCTree get(String key) {
		if (this.isTopSymbolTable) {
			return dictionary.get(key);
		}
		if (!containsCurrent(key)) {
			return parentTable.get(key);
		}
		return dictionary.get(key);
	}
	
	public void remove(String key) {
		if (this.isTopSymbolTable) {
			dictionary.remove(key);
		} else if (!containsCurrent(key)) {
			parentTable.remove(key);
		} else {
			dictionary.remove(key);
		}
	}
	
	/** This method is used by the ReturnStat to check that the expression it holds is the one expected by the current scope.
	 * @param returnType
	 * 		The WACCType of the ExprNode in the ReturnStat
	 * @return
	 * 		true iff a return of type `returnType` was expected
	 */
	public boolean checkType(WACCType returnType) {
		return expectation.checkType(returnType);
	}
	
	public void finaliseScope(String funcName) {
		if(!expectation.isResolved()) {
			new UnresolvedExpectationException("The expectation of the function " + funcName + " were not met.");
		}
	}

	public void update(String key, WACCTree node) {
		if (this.isTopSymbolTable) {
			dictionary.remove(key);
			add(key, node);
		} else if (!containsCurrent(key)) {
			parentTable.update(key, node);
		} else {
			dictionary.remove(key);
			add(key, node);
		}
	}
}