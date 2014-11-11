package tree;


public class ArrayTypeNode implements WACCType {
	
	private WACCType baseType;
	
	public ArrayTypeNode(WACCType baseType) {
		this.baseType = baseType;
	}

	@Override
	public boolean isCompatible(WACCType other) {
		if (!(other instanceof ArrayTypeNode)) {
			return false;
		}
		ArrayTypeNode otherArray = (ArrayTypeNode) other;
		if (!baseType.isCompatible(otherArray.getBaseType())) {
			return false;
		}
		return true;
	}

	private WACCType getBaseType() {
		return baseType;
	}

}
