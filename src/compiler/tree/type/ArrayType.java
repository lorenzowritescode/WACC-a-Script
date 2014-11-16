package tree.type;


public class ArrayType extends WACCType {
	
	private WACCType baseType;
	
	public ArrayType(WACCType baseType) {
		this.baseType = baseType;
	}

	@Override
	public boolean isCompatible(WACCType other) {
		if (!(other instanceof ArrayType)) {
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
