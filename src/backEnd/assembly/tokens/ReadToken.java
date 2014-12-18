package assembly.tokens;

import tree.type.WACCType;
import assembly.InstrToken;
import assembly.TokenSequence;
import assembly.system.SystemFormatterTokens;
import assembly.system.SystemReadTokens;

public class ReadToken extends InstrToken {
	private WACCType type;

	public ReadToken(WACCType type) { 
		this.type = type;
	}

	@Override
	public TokenSequence toPrepend() {
		TokenSequence pre = new TokenSequence();
		if (type == WACCType.INT)
			return pre.append(SystemFormatterTokens.INT_FORMATTER);
		if (type == WACCType.CHAR)
			return pre.append(SystemFormatterTokens.CHAR_FORMATTER);
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}

	@Override
	public TokenSequence toAppend() {
		TokenSequence post = new TokenSequence();
		if (type == WACCType.INT)
			return post.append(SystemReadTokens.READ_INT);
		if (type == WACCType.CHAR)
			return post.append(SystemReadTokens.READ_CHAR);
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}

	@Override
	public String toString() {
		if (type == WACCType.INT)
			return new BranchLinkToken("p_read_int").toString();
		if (type == WACCType.CHAR)
			return new BranchLinkToken("p_read_char").toString();
		else
			throw new IllegalArgumentException("Can only read ints or chars");
	}
}
