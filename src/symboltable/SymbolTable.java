package symboltable;

import java.util.HashMap;

public class SymbolTable {
	
	HashMap<String, Identifier> dictionary;
	SymbolTable parentTable;
	
	public SymbolTable(SymbolTable parentTable){
		this.parentTable = parentTable;
		this.dictionary = new HashMap<>();
	}
	
}