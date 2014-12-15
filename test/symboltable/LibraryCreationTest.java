package symboltable;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tree.type.WACCType;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import jsparser.LibArg;
import jsparser.LibArgList;
import jsparser.LibFunc;
import jsparser.LibType;

public class LibraryCreationTest {
	LibArg arg = new LibArg(LibType.NUMBER);
	LibArgList l = new LibArgList(arg);
	LibFunc func = new LibFunc("FunctionYay!",LibType.CHAR, l);
	List<LibFunc> lib = new ArrayList<LibFunc>();
	boolean b = lib.add(func);
	
	
	
	@Test
	public void symbolTablesConstructedWithExternalLibrariesContainTheCorrectObjects() {
		
		SymbolTable st = new SymbolTable(lib);
		assertThat(st.containsRecursive("FunctionYay!"), is(true));
		assertThat(st.get("FunctionYay!").getType(), is(WACCType.CHAR));
		
	}

}
