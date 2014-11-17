package tree.type;


public class ArrayType extends WACCType {
	
	private WACCType baseType;
	
	public ArrayType(WACCType baseType) {
		this.baseType = baseType;
	}

	@Override
	public boolean isCompatible(WACCType other) {
		
		if (!(other instanceof ArrayType)) {
			if(!baseType.isCompatible(other)) {
				return false;
			}
			return true;
		}
		if (!(baseType == WACCType.NULL)) {
			ArrayType otherArray = (ArrayType) other;
			if ( otherArray.getBaseType() == WACCType.NULL) {
				return true;
			} else if (!baseType.isCompatible(otherArray.getBaseType())) {
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
		return "array-" + baseType.toString();
	}

}
