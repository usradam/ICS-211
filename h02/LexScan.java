package h02;

import java.util.Arrays;

public class LexScan {

	//CLass-specific method lexicalScanner()
	public static String[] lexicalScanner(String input) {

		//Create a result variable for our tokens
		String[] result = new String[input.length()];
		int index = 0;

		//Create a loop that looks at each character 1 at a time
		for(int i = 0; i < input.length(); i++) {

			//Assign a variable to hold the character we're looking at
			char c = input.charAt(i);

			//==================================================================
			//========================= JAVA IDENTIFIER ========================
			//==================================================================

			//Check if 'c' is a Java Identifier start
			if(Character.isJavaIdentifierStart(c)) {

				//Create a token variable to hold our characters as we build the token
				String token = "";

				//Build our Java Identifier token
				while(i < input.length() && Character.isJavaIdentifierPart(c)) {

					token += c;
					i++;
					if(i < input.length())
						c = input.charAt(i);

				}

				//Add our token to the result array
				result[index] = token;

				//Fix our variables to build the next token
				index++;
				i--;
				continue;
			}


			//==================================================================
			//============================== DIGIT =============================
			//==================================================================

			//Check if 'c' is a Digit
			else if(Character.isDigit(c)) {

				//Create a token variable to hold our characters as we build the token
				String token = "";

				//Build our Digit token
				while(i < input.length() && Character.isDigit(c)) {

					token += c;
					i++;
					if(i < input.length())
						c = input.charAt(i);

				}

				//Add our token to the result array
				result[index] = token;

				//Fix our variables to build the next token
				index++;
				i--;
				continue;
			}



			//==================================================================
			//========================= SPECIAL CHARACTER ======================
			//==================================================================

			//Check if 'c' is NOT a whitespace
			else if(!Character.isWhitespace(c)) {

				//Create a token variable to hold our character as we build the token				
				String token = Character.toString(c);

				//Add our token to the result array
				result[index] = token;

				//Fix our variables to build the next token
				index++;
			}

		}

		//Return our result array
		return Arrays.copyOf(result, index);

	}

	//Unit test method unitTest()
	public static boolean unitTest() {

		//Create a variable for the return value
		boolean result = true;

		//===========================
		//==========Test 1===========
		//===========================

		String input1 = "a2 = a1 + b22;";
		String[] expectedResult1 = {"a2", "=", "a1", "+", "b22", ";"};
		String[] actualResult1 = lexicalScanner(input1);

		for(int i = 0; i < expectedResult1.length; i++) {
			if(!expectedResult1[i].equals(actualResult1[i])) {
				System.out.println("Test 1 failed on index " + i);
				result = false;
			}
		}

		//===========================
		//==========Test 2===========
		//===========================
		String input2 = "45 = one + two + three + four + five + six + seven + eight + nine;";
		String[] expectedResult2 = {"45", "=", "one", "+", "two", "+", "three", "+", "four",
		"+", "five", "+", "six", "+", "seven", "+", "eight", "+", "nine", ";"};
		String[] actualResult2 = lexicalScanner(input2);

		for(int i = 0; i < expectedResult2.length; i++) {
			if(!expectedResult2[i].equals(actualResult2[i])) {
				System.out.println("Test 2 failed on index " + i);
				result = false;
			}
		}
		
		//===========================
		//==========Test 3===========
		//===========================
		String input3 = "a = b + 2;";
		String[] expectedResult3 = {"a", "=", "b", "+", "2", ";"};
		String[] actualResult3 = lexicalScanner(input3);
		
		for(int i = 0; i < expectedResult3.length; i++) {
			if(!expectedResult3[i].equals(actualResult3[i])) {
				System.out.println("Test 3 failed on index " + i);
				result = false;
			}
		}
		
		//===========================
		//==========Test 4===========
		//===========================
		String input4 = "Homework 2 = Due on Saturday;";
		String[] expectedResult4 = {"Homework", "2", "=", "Due", "on", "Saturday", ";"};
		String[] actualResult4 = lexicalScanner(input4);
		
		for(int i = 0; i < expectedResult4.length; i++) {
			if(!expectedResult4[i].equals(actualResult4[i])) {
				System.out.println("Test 4 failed on index " + i);
				result = false;
			}
		}
		
		
		
		//Return result of the unit test
		return result;
		
	}



	//Main method
	public static void main(String[] args) {
		
		System.out.println("Adam Graham");
		boolean testResult = unitTest();
		
		if(testResult == true) {
			System.out.println("Success");
		}
		else {
			System.out.println("Test failed");
		}

	}

}
