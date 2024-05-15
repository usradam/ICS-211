package h05;

public class HW05 {

	static String intToString(int toPrint, int base) {
		
		if (toPrint < base) {

			if(base <= 10)
				return ("" + toPrint);
			else {
				// TODO: Convert from integer to the appropriate letter (10 == a, 11 ==b, etc)
				if(toPrint >= 10) {
					int remainder = toPrint;
					return String.valueOf((char) ('a' + remainder - 10));
				}
				
				return String.valueOf(toPrint);
			}
		} else

			if(base <= 10)
				return intToString(toPrint / base, base) + (toPrint % base);
			else {
				// TODO: Make the same recursive call, but convert the remainder to the appropriate letter/number
				int remainder = toPrint % base;
				
				if(remainder >= 10) {
					String stringRemainder = String.valueOf((char) ('a' + remainder - 10));					
					return intToString(toPrint / base, base) + stringRemainder;
					
				}
				
				return intToString(toPrint / base, base) + (toPrint % base);
			}
	}


	// intToString(22, 10)
	// return intToString(2 / 10, 10) + (22 % 10)
	// --> becomes return "2" + "2"
	// --> concatenates to return "22";


	// intToString(2, 10)
	// return "" + 2; or return "2";

	public static String[] firstTernaryNumbers(int n) {
		String[] result = new String[n];

		for(int i = 0; i < n; i++) {
			result[i] = intToString(i, 3);
		}

		return result;
	}
	
	public static String[] firstHexNumbers(int n) {
		// TODO: Convert the numbers to hexadecimal instead of ternary
		String[] result = new String[n];
		
		for(int i = 0; i < n; i++) {
			result[i] = intToString(i, 16);
		}
		
		return result;
	}
		
	
	
	static int fib3(int n) {
		// TODO: Implement the logic for the fibonacci sequence, but make each number
		// the sum of the previous '3' numbers instead of '2'
		if(n < 3) {
			return 1;
		}
		return fib3(n - 1) + fib3(n - 2) + fib3(n - 3);
	}

	

	//	binary == base 2
	//	0, 1
	//	00, 01, 10, 11, 100, 101, 110, 111, 1000, 1001, ...
	//	
	//	ternary == base 3
	//	0, 1, 2
	//	0, 1, 2, 10, 11, 12, 20, 21, 22, 100, 101, 102, 110, ...
	//	
	//	decimal == base 10
	//	0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	//	
	//	hexadecimal == base 16
	//	0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f
	//	10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1a, 1b, 1c, 1d, 1e, 1f, 20, 21, ...


	// a == 97
	// z = 122



	// Number to convert: 15
	// 15 - 10 = 5			<-- Subtract 10 from number to convert
	// 97 + 5 = 102			<-- Add the difference to 97, a.k.a the integer representation of 'a'

	// 10 - 10 = 0
	// 97 + 0 = 97


	public static void main(String[] args) {
		String[] ternary = firstTernaryNumbers(31);
		
		// TODO: Print the values inside of the 'ternary' array (similar to firstTernaryNumbers(int n))
		System.out.println("---- Ternary (0-30) ----");
		for(int i = 0; i < ternary.length; i++) {
			System.out.println(ternary[i]);
		}
		
		String[] hex = firstHexNumbers(525);
		System.out.println("---- Hex (500-524) ----");
		for(int i = 0; i < hex.length; i++)
			System.out.println(hex[i]);
		
		System.out.println("---- Fib (0-11) ----");
		for(int i = 0; i < 12; i++) {
			System.out.println(fib3(i));
		}
		
		long timeStart = System.currentTimeMillis();
		
		fib3(35);
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time taken: " + (timeEnd - timeStart) + " milliseconds");
	}
}
		


//
//		for(int i = 0; i < 150; i++) {
//			System.out.println(i + ": " + (char)i);
//		}
		

