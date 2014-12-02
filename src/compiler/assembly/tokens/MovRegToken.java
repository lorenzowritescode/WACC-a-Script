package assembly.tokens;

import assembly.Register;

public class MovRegToken extends MovToken {
	
	public MovRegToken(String condition, Register rDest, Register rSrc){
		this.dest = rDest;
		this.regSource = rSrc;
		this.condition = condition;
		this.addRegister(rDest, rSrc);
	}
	
	/**
	 * Constructor for register to register copy.
	 * @param rDest The destination register
	 * @param rSrc  The source register
	 */
	public MovRegToken(Register rDest, Register rSrc){
		this.dest = rDest;
		this.regSource = rSrc;
		this.addRegister(rDest, rSrc);
	}
	
	@Override
	public String toString() {
		return "\tMOV" + condition + " " + dest.toString() + ", " + regSource.toString();
	}
	
}
