package WACCExceptions;

import java.util.ArrayList;

/*
 * The error listener is used to record semantic error during compilation,
 * and then print them out to the console once compilation is finished.
 */

public class ErrorListener {
	
	ArrayList<WACCException> errorList;
	
	public ErrorListener() {
		this.errorList = new ArrayList<>();
	}
	
	public void record(WACCException e) {
		this.errorList.add(e);
	}
	
	public String printErrors() {
		String errorPrintout = "";
		for (WACCException e : errorList) {
			errorPrintout += e.toString();
		}
		
		return errorPrintout;
	}

	public int errorCount() {
		return errorList.size();
	}

	/**
	 * This method is used to signal to the Error Listener that the Semantic Checking is now complete.
	 * If errors are present, they are printed to stderr. 
	 * @return
	 * 		true iff the error listener is empty.
	 */
	public boolean finish() {
		boolean correctProgram = errorCount() == 0;
		if (!correctProgram) {
			System.err.println(printErrors());
		}
		return correctProgram;
	}
}
