package h09;

public class Main {
	
	public static void main(String[] args) {
		
		StackCalculator stackCalculator = new StackCalculator();
		String[] input = {"2", "2", "+", "3", "*", "22", "20", "-", "/", "4", "%", "8", "^"};
		String result = stackCalculator.calculate(input);
		
		System.out.println(result);
		
		// TODO: Test the calculate method with the command-line arguments (args)
		StackCalculator stackCalculatorCommandLine = new StackCalculator();
		String resultCommand = stackCalculatorCommandLine.calculate(args);

		System.out.println(resultCommand);
		
	}

}
