package symboltable;

import java.util.HashMap;

public class SymbolTable {
	
	HashMap<String, Identifier> dictionary;
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

	public void add(String name, Identifier nodeIdentifier) {
		dictionary.put(name, nodeIdentifier);
	}

	public SymbolTable getParent() {
		return this.parentTable;
	}

}