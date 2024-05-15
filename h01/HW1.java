package h01;

import java.util.Arrays;

public class HW1 {

	public static void main(String[] args) {
		
		// PART 1:
		Roots r = new Roots ();
		int randInt = (int) (Math.random()*11);
		double[] rootNums = r.roots(randInt);
		System.out.println(Arrays.toString(rootNums));
		
		
		
		// PART 2:
		Reader reader = new Reader();
		for(int i = 0; i < args.length; i++) {
			int count = reader.numChars(args[i]);
			if(count == -1) {
				System.out.println(args[i] + " not found");
			}
			else {
				System.out.println(args[i] + " " + count);
			}
		}
	}

}
