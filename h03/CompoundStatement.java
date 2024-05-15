package h03;

public abstract class CompoundStatement extends Statement {

	@Override
	public boolean isCompound() {
		return true;
	}
	
	public abstract int numberOfParts();
	
}
