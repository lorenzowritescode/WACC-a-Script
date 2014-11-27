package assembly;

public class Register {
	
	private Integer registerNumber;
	private Register next;
	
	public Register(String varName) {
		this.next = new Register();
	}
	public Register() {
		this.next = new Register();
	}
	
	public void setRegister(int reg) {
		this.registerNumber = reg;
	}
	
	public Register getNext() {
		return next;
	}
	
	@Override
	public String toString() {
		if (registerNumber == null) {
			throw new IllegalStateException("The register for this object was not set.");
		}
		
		return "r" + registerNumber;
	}
	

	public static final Register R0 = new Register() {
		@Override
		public String toString() {
			return "r0";
		}
		
		@Override
		public Register getNext() {
			return R1;
		}
	};
	public static final Register R1 = new Register() {
		@Override
		public String toString() {
			return "r1";
		}
		
		@Override
		public Register getNext() {
			return R2;
		}
	};
	public static final Register R2 = new Register() {
		@Override
		public String toString() {
			return "r2";
		}
		
		@Override
		public Register getNext() {
			return R3;
		}
	};

	public static final Register R3 = new Register() {
		@Override
		public String toString() {
			return "r3";
		}
		
		@Override
		public Register getNext() {
			throw new UnsupportedOperationException("Can't request a register after R3");
		}
	};
}
