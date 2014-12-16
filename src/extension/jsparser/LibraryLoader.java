package jsparser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsparser.types.LibArray;
import jsparser.types.LibBaseType;
import jsparser.types.LibPair;
import jsparser.types.LibType;
import symboltable.SymbolTable;
import tree.func.FuncDecNode;
import tree.func.ParamListNode;
import tree.func.ParamNode;
import tree.type.ArrayType;
import tree.type.PairType;
import tree.type.WACCType;
import WACCExceptions.InvalidTypeException;

public class LibraryLoader {
	
	private SymbolTable st;
	
	public LibraryLoader(SymbolTable st) {
		this.st = st;
	}
	
	//Creates a symbol table already containing the Library functions passed to it
	public SymbolTable loadIntoSymbolTable(List<LibFunc> funcs) {
		for(LibFunc f : funcs) {
			//Create the WACC Param list node to pass to the FuncDecNode
			ParamListNode params = new ParamListNode();
			for (LibArg arg : f.getArgList()) {
				LibType argType = arg.getType();
				WACCType argWACCType = JStoWACCTypeEval(argType);
					
				//The ident of the parameter is irrelevant, so a null is passed to ParamNode constructor
				ParamNode param = new ParamNode(argWACCType, null);
				params.add(param);
			}
				
			//FuncDecNode is created and added to the symbol Table
			LibType libType = f.getReturnType();
			WACCType type = JStoWACCTypeEval(libType);
			FuncDecNode func = new FuncDecNode(type, f.getFunctionName(), params);
			st.add(f.getFunctionName(), func);
		}
		return st;
	}
		
	private WACCType JStoWACCTypeEval(LibType type) {
		if (type instanceof LibArray) {
			LibArray array = (LibArray) type;
			WACCType innerType = JStoWACCTypeEval(array.getInner());
			return new ArrayType(innerType);
		} else if (type instanceof LibPair) {
			LibPair pair = (LibPair) type;
			WACCType fstType = JStoWACCTypeEval(pair.getFst());
			WACCType sndType = JStoWACCTypeEval(pair.getSnd());
			return new PairType(fstType, sndType);
		} else {
			LibBaseType baseType = (LibBaseType) type;
			switch (baseType) {
			case NUMBER:
				return WACCType.INT;
			case BOOL:
				return WACCType.BOOL;
			case CHAR:
				return WACCType.CHAR;
			case STRING:
				return WACCType.STRING;
			default:
				throw new InvalidTypeException("The type provided was not recognised: " + type.getTypeString());
			}
		}
	}

	public Map<String, String> findDependancies(String libName, List<LibFunc> funcs) {
		HashMap<String, String> map = new HashMap<>();
		for (LibFunc func : funcs) {
			String name = func.getFunctionName();
			map.put(name, libName);
		}
		return map;
	}

}
