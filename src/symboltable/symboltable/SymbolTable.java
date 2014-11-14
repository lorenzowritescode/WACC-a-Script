package symboltable;

import java.util.HashMap;

import tree.WACCTree;

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
	
	public WACCTree get(String key) {
		if (this.isTopSymbolTable) {
			return dictionary.get(key);
		}
		if (!containsCurrent(key)) {
			return parentTable.get(key);
		}
		return dictionary.get(key);
	}
}