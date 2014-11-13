package WACCExceptions;

import java.util.ArrayList;

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
}
