package jsparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LibArgList implements Iterable<LibArg>{
	private List<LibArg> args;

	public LibArgList(LibArg...args) {
		this.args = new ArrayList<>(Arrays.asList(args));
	}

	@Override
	public Iterator<LibArg> iterator() {
		return args.iterator();
	}

	public void add(LibArg a) {
		args.add(a);
	}
}
