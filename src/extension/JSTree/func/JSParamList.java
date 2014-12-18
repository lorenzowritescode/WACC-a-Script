package JSTree.func;

import java.util.List;

import JSTree.JSTree;

public class JSParamList extends JSTree {
	
	private List<JSParam> params;
	
	public JSParamList(List<JSParam> params) {
		this.params = params;
	}
	
	@Override
	public String toCode() {
		return JSFunc.encodeArgs(params);
	}
	
	public void addCallback() {
		params.add(new JSParam("callback"));
	}

}
