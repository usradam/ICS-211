package h03;

public class Conditional extends CompoundStatement {

	@Override
	public int numberOfParts() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public Conditional(String[] input) throws InvalidStatementException {
		
		boolean valid = true;
		
		// Check #1
		// input[0] should be "if"
		if(!input[0].equals("if"))
			valid = false;
		
		// Check #2
		// input[1] should be "("
		if(!input[1].equals("("))
			valid = false;
		
		// Check #3
		// input should contain "else"
		boolean foundElse = false;
		for(int i = 0; i < input.length; i++) {
			if(input[i].equals("else"))
				foundElse = true;
		}
		
		if(foundElse == false)
			valid = false;
		
		// Check #4
		// Last token is "}"
		if(!input[input.length - 1].equals("}"))
			valid = false;
		
		
		// If/else block
		if(valid) {
			tokens = input;
			
		}
		
		else {
			throw new InvalidStatementException();
		}
		
	}

}
