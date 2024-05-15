package h03;

public class Assignment extends BasicStatement implements Comparable {
	
	//num = 5 + 4 - 3 / (Math.sqrt(9));
	
	public String getVariable() {
		return tokens[0];
	}
	
	public Assignment(String[] input) throws InvalidStatementException {
		
		boolean valid = true;
		
		// Check #1
		// input[0] should be a java identifier
		if(isJavaIdentifier(input[0]) == false)
			valid = false;
		
		// Check #2
		// input[1] should be "="
		if(!input[1].equals("="))
			valid = false;
		
		// Check #3
		// last token should be ";" (input[input.length - 1])
		if(!input[input.length - 1].equals(";"))
			valid = false;
		
		// If the set of tokens is valid, save it to the class variable
		if(valid) {
			tokens = input;
		}
		
		//If they are not valid, throw exception
		else {
			throw new InvalidStatementException();
		}
	}
	
	public int compareTo(Object o) {
		Statement st = (Statement) o;
		return this.tokens.length - st.tokens.length;
	}
}
