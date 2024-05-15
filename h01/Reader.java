package edu.ics211.h01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	
	public int numChars(String fileName) {
		
		File file = new File(fileName);
		try {
			Scanner reader = new Scanner(file);
			int count = 0;
		
			while(reader.hasNext()) {
				String token = reader.next();
				count += token.length();
		}	
		return count;
		
	} catch (FileNotFoundException e) {
		return -1;
	
		}

	}
	
}
