package h03;

public class BasicStatement extends Statement {

	public BasicStatement(String[] tokens) {
		// TODO Auto-generated constructor stub	
		this.tokens = tokens;
	}
	
	public BasicStatement() {
		
	}

	@Override
	public boolean isCompound() {
		
		return false;
	}
	
	@Override
	public int numTokens() {
		
		return tokens.length;
	}
	
	@Override
	public String getToken(int index) {
		if(index < 0 || index >= tokens.length) {
			throw new ArrayIndexOutOfBoundsException();
			
		}
		
		return tokens[index];
	}

}
