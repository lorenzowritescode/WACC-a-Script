package tree.type;

import assembly.InstrToken;
import assembly.Register;
import assembly.TokenSequence;
import assembly.tokens.PrintReferenceToken;
import assembly.tokens.StorePreIndexToken;
import assembly.tokens.StoreToken;

/* Class for the Array type
 * Contains the Array's Base Type
 * Functionality to check if other types are compatible with the array
 * Rule: type '[' ']'
 */

public class ArrayType extends WACCType {
	
	private WACCType baseType;
	protected final int VAR_SIZE = 4;
	
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

	public WACCType getBaseType() {
		return baseType;
	}
	
	@Override
	public String toString() {
		return "array-" + baseType.toString();
	}

	@Override
	public InstrToken passAsArg(Register r) {
		return new StorePreIndexToken(Register.sp, r, -VAR_SIZE  );
	}
	
	@Override
	public int getVarSize() {
		return VAR_SIZE;
	}
	
	@Override
	public StoreToken storeAssembly(Register dest, Register source) {
		return new StoreToken(dest, source);
	}
	
	@Override
	public TokenSequence printAssembly(Register register) {
		return new TokenSequence(new PrintReferenceToken(register));
	}

}
