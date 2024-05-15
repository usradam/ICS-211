package h03;

public abstract class Statement implements StatementInterface {
	
	protected String[] tokens;
	
	abstract public boolean isCompound();
	
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
	
	public static boolean isJavaIdentifier(String token) {
	
		// Check if first character is the start of a Java Identifier
		if(!Character.isJavaIdentifierStart(token.charAt(0))) {
			return false;
		}
		
		// Check if every other character is part of a Java Identifier	
		for(int i = 0; i < token.length(); i++) {
			if(!Character.isJavaIdentifierPart(token.charAt(i))) {
				return false;
				}
			}
		// Return true only if all checks pass
		return true;

	}
	
	@Override
	public String toString() {
		
		// Initialize StringBuilder to build a resulting string
		StringBuilder result = new StringBuilder();
		
		// Iterate through each token for the length of the tokens array
		// for loop concatenates each token into the resulting string
		for (int i = 0; i < tokens.length; i++) {
	        result.append(tokens[i]);
	        
	        // Adds a space unless it's the last token
	        if (i < tokens.length - 1) {
	            result.append(" ");
	        }
	    }
		return result.toString();
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		// Check our parameters
		if(obj == null) return false;
		
		// Cast 'obj' to a statement variable
		Statement st = (Statement) obj;
		
		// Check that the token arrays are the same length
		if(tokens.length != st.tokens.length) 
			return false;
		
		// Check that all elements match across both arrays
		for(int i = 0; i < tokens.length; i++) {
			if(!tokens[i].equals(st.tokens[i]))
				return false;
		}
		
		// If all checks pass, these statements are equal
		return true;
			
		
	}
	
}
