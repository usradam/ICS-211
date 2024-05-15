package h03;

public class MethodCall extends BasicStatement {

	// roots(number);
	
	public String getMethodName() {
		return tokens[0];
		
	}
	
	public MethodCall(String[] input) throws InvalidStatementException {
		boolean valid = true;
		
		// Check #1
		// input[0] should be a Java Identifier
		if(isJavaIdentifier(input[0]) == false)
			valid = false;
		
		
		// Check #2
		// input[1] is the token "("
		if(!input[1].equals("("))
			valid = false;
		
		
		// Check #3
		// Second to last token is a ")"
		if(!input[input.length - 2].equals(")"))
			valid = false;
		
		// Check #4
		// last token should be a ";"
		if(!input[input.length - 1].equals(";"))
			valid = false;
		
		// If the set of tokens is valid, save it to the class variable
		if(valid) {
			tokens = input;
			
		}
		
		//If they are not valid, throw an exception
		else {
			throw new InvalidStatementException();
			
		}
	}
}
