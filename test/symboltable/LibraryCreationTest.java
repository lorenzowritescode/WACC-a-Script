package symboltable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import jsparser.LibArg;
import jsparser.LibArgList;
import jsparser.LibFunc;
import jsparser.LibraryLoader;
import jsparser.types.LibBaseType;

import org.junit.Test;

import tree.type.WACCType;

public class LibraryCreationTest {
	LibArg arg = new LibArg(LibBaseType.NUMBER);
	LibArgList l = new LibArgList(arg);
	LibFunc func = new LibFunc("FunctionYay!",LibBaseType.CHAR, l);
	List<LibFunc> lib = new ArrayList<LibFunc>();
	boolean b = lib.add(func);
	
	
	
	@Test
	public void symbolTablesConstructedWithExternalLibrariesContainTheCorrectObjects() {
		SymbolTable st = new SymbolTable();
		LibraryLoader ll = new LibraryLoader(st);
		ll.loadIntoSymbolTable(lib);
		assertThat(st.containsRecursive("FunctionYay!"), is(true));
		assertThat(st.get("FunctionYay!").getType(), is(WACCType.CHAR));
		
	}

}
