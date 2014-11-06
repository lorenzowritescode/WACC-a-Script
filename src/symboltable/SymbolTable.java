package symboltable;

import java.util.HashMap;

public class SymbolTable {
	
	HashMap<String, Identifier> dictionary;
	SymbolTable parentTable;
	final boolean isTopSymbolTable;
	private String currentScope;
	
	public SymbolTable(SymbolTable parentTable){
		this.parentTable = parentTable;
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = false;
	}

	public SymbolTable() {
		this.dictionary = new HashMap<>();
		this.isTopSymbolTable = true;
	}

	public boolean contains(String identifier) {
		if(this.isTopSymbolTable){
			return dictionary.containsKey(identifier);
		}
		return dictionary.containsKey(identifier) || parentTable.contains(identifier);
	}

	public void add(String name, Identifier nodeIdentifier) {
		if (nodeIdentifier instanceof FunctionIdentifier) {
			this.currentScope = name;
		}
		dictionary.put(name, nodeIdentifier);
	}

	public String getCurrentFuction() {
		return this.currentScope;
	}
	
}