package h09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnitTest {
	
	// Instance variable
	StackCalculator stackCalculator = new StackCalculator();
	
	@Test
	void testAllOperators() {
		String[] args = {"2", "2", "+", "3", "*", "22", "20", "-", "/", "4", "%", "8", "^"};
		String expectedOutput = "4\n12\n2\n6\n2\n256\n";
		assertEquals(expectedOutput, stackCalculator.calculate(args));
	}
	
	
	@Test
	void testNumberFormatError() {
		String[] args = {"2", "hello", "+", "world", "1", "-"};
		String expectedOutput = "syntax error\nsyntax error\nsyntax error\n1\n";
		assertEquals(expectedOutput, stackCalculator.calculate(args));
	}
	
	@Test
	void testDivisorIsZero() {
		String[] args = {"3", "7", "+","5", "/","8", "0", "/"};
		String expectedOutput = "10\n2\nError: Division by 0\n";
		assertEquals(expectedOutput, stackCalculator.calculate(args));
	}

}
