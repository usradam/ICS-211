package h03;

@SuppressWarnings("serial")
public class InvalidStatementException extends Exception {

	// No arguments constructor
	public InvalidStatementException() {
		super();
		
	}
		
	// String argument constructor
	public InvalidStatementException(String error) {
		super(error);
		
	}
}
