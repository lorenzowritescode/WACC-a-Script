package jsparser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LibArgList implements Iterable<LibArg>{
	private List<LibArg> args;

	public LibArgList(LibArg...args) {
		this.args = Arrays.asList(args);
	}

	@Override
	public Iterator<LibArg> iterator() {
		return args.iterator();
	}
}
