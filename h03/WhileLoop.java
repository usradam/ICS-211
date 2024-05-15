package h03;

public class WhileLoop extends CompoundStatement {

	@Override
	public int numberOfParts() {

		return 1;
	}
	
	public WhileLoop(String[] input) throws InvalidStatementException {
		
		boolean valid = true;
		
		// Check #1
		// input[0] is "while"
		if(!input[0].equals("while"))
			valid = false;
		
		
		// Check #2
		// input[1] is "("
		if(!input[1].equals("("))
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
