package symboltable;

import java.util.HashMap;

import org.antlr.v4.runtime.RuleContext;

import tree.WACCTree;
import WACCExceptions.NotUniqueIdentifierException;

public class SymbolTable {
	
	HashMap<String, WACCTree> dictionary;
	SymbolTable parentTable;
	final boolean isTopSymbolTable;
	
	public SymbolTable(SymbolTable parentTable){
		this.parentTable = parentTable;
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = false;
	}

	public SymbolTable() {
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = true;
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
}