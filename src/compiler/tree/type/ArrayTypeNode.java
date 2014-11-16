package tree.type;


public class ArrayTypeNode extends WACCType {
	
	private WACCType baseType;
	
	public ArrayTypeNode(WACCType baseType) {
		this.baseType = baseType;
	}

	@Override
	public boolean isCompatible(WACCType other) {
		if (!(other instanceof ArrayTypeNode)) {
			return false;
		}
		if (!(baseType == null)) {
			ArrayTypeNode otherArray = (ArrayTypeNode) other;
			if (!baseType.isCompatible(otherArray.getBaseType())) {
				return false;
			}
			return true;
		}
		return true;
	}

	private WACCType getBaseType() {
		return baseType;
	}
	
	@Override
	public String toString() {
		return "array";
	}

}
