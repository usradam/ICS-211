package h09;

public class StackCalculator {

	// Instance variables
	ArrayStack stack1 = new ArrayStack();
	LinkedStack stack2 = new LinkedStack();

	public String calculate(String[] args) {

		StringBuilder stringBuilder = new StringBuilder();

		// Loop through each element of our String[] args
		for(String arg : args) {

			// =========================================
			// ===== CASE 1: String is an operator =====
			// =========================================

			// Check to see if 'arg' is a valid operator
			if("+-*/%^".contains(arg)) {

				// If so, we need to pop 2 values from the stack to perform the operation
				if(stack1.size < 2) {
					stringBuilder.append("syntax error");
					stringBuilder.append("\n");
				}
				else {
					
					long secondOperand = stack1.pop();
					long firstOperand = stack1.pop();
					long result = 0;

					switch(arg) {
					case "+" -> result = firstOperand + secondOperand;
					case "-" -> result = firstOperand - secondOperand;
					// TODO: Complete cases for remaining operators
					case "*" -> result = firstOperand * secondOperand;
					case "/" -> {
						if(secondOperand == 0) {
							stringBuilder.append("Error: Division by 0");
							stringBuilder.append("\n");
							return stringBuilder.toString();
						}
						else {
							result = firstOperand / secondOperand;
						}
					}
					case "%" -> result = firstOperand % secondOperand;
					case "^" -> result = (long) Math.pow(firstOperand, secondOperand);

					default -> {}
					}
					// TODO: Push the result of the operation onto the stack
					stack1.push(result);

					// TODO: Append the result and newline onto the stringBuilder
					stringBuilder.append(result);
					stringBuilder.append("\n");
				}
			}

			// ======================================
			// ===== CASE 2: String is a number =====
			// ======================================

			else {
				// Try to parse the string as a number. If it's not a number, print syntax error
				long value = 0;
				try {
					value = Long.parseLong(arg);
					stack1.push(value);
				} catch(NumberFormatException e) {
					stringBuilder.append("syntax error");
					stringBuilder.append("\n");
				}		
			}
		}
		return stringBuilder.toString();
	}
}
