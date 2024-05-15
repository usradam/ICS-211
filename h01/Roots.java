package edu.ics211.h01;

public class Roots {

	public double[] roots(int num) {
		if(num < 2) {
			return new double [0];
		}
		
		double[] result = new double[num-1];
		for(int i = 0; i < result.length; i++) {
			result[i] = Math.sqrt(i+2);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
	
		Roots r = new Roots();
		double[] test = r.roots(5);
	
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	
	}

}