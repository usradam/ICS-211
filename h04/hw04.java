package h04;


public class hw04 {
	/* @authors: esb@hawaii.edu, 2023 & adamgra@hawaii.edu, 2023 */

	  enum SortType {
	    SelectionSort,
	    SelectUnique,
	    BubbleSort,
	    InsertionSort,
	  };

	  private static void swap(char[] a, int i1, int i2) {
	    // student must implement
		  char temp = a[i1];
		  a[i1] = a[i2];
		  a[i2] = temp;
	  }

	  static void selectionSort(char[] a) {
	    // student must implement
		  int n = a.length;	// Get length of array
		  
		  for(int i = 0; i < n - 1; i++) {	// Outer loop iterates of each element in array except last one
			  int smallestIndex = i;		// Initialize smallest element to be at current index
			  
			  for(int j = i + 1; j < n; j++ ) {	// Inner loop iterates over the rest of the array
				  if(a[j] < a[smallestIndex]) {	// If smaller element found, update smallestIndex
						smallestIndex = j;
				  }
			  }
			  
			  if(smallestIndex != i) {	// If smallest element not at current index, swap them
				  swap(a, i, smallestIndex);
			  }
		  }
	  }

	  // like selection sort, but only returns unique elements
	  static String selectUnique(char[] a) {
	    // student must implement
		  
		  // Repeated loop as implemented above for selectionSort
		  int n = a.length;
		  
		  for(int i = 0; i < n - 1; i++) {
			  int smallestIndex = i;
			  
			  for(int j = i + 1; j < n; j++ ) {
				  if(a[j] < a[smallestIndex]) {
						smallestIndex = j;
				  }
			  }
			  
			  if(smallestIndex != i) {
				  swap(a, i, smallestIndex);
			  }
		  }
		  
		  int uniqueChars = 1; // Initialize unique character counter
		  
		  for(int i = 1; i < n; i++) {   // Iterate over array starting at second element
			  if(a[i] != a[i - 1]) {     // Compare character and the one before it
				  a[uniqueChars] = a[i]; // Move character to next position in array
				  uniqueChars++;		 // Increment counter for unique character
			  }
		  }
		  
	    return new String(a, 0, uniqueChars);  // Return string of unique characters
	  }

	  static void bubbleSort(char[] a) {
	    // student must implement
		  int n = a.length; // Get the length of the array
		  
		  for(int i = 0; i < n; i++) { 			// Outer loop iterates over each element in the array
			  for(int j = 1; j < n - i; j++) {  // Iterate starting from second element to the end of the unsorted array
				  if(a[j - 1] > a[j]) {			// If current element greater than the one after 
					  swap(a, j - 1, j);		//Swap current element with the next one
				  }
			  }
		  }
	  }

	  static void insertionSort(char[] a) {
	    // student must implement
		  int n = a.length;	// Get the length of the array
		  
		  for(int i = 1; i < n; i++) { 		// Start iterating over array starting at the second element
			  char key = a[i]; 				// Create the 'key' element which will inserted in sorted position
			  int j = i - 1; 				// Start at element right before the 'key' element
			  
			  while(j >= 0 && a[j] > key) { 	// Move any element greater than the 'key' ahead of it
				  swap(a, j + 1, j); 			// As long as while loop is true, swap elements
				  j = j - 1; 					// Move backwards in sorted array
			  }
			  
			  a[j + 1] = key; 	// Insert key in it's sorted position
		  }
	  }

	  static String sortChars(String s, SortType sort) {
	    char[] a = s.toCharArray();
	    switch (sort) {
	    case SelectionSort:
	      selectionSort(a);
	      break;
	    case BubbleSort:
	      bubbleSort(a);
	      break;
	    case InsertionSort:
	      insertionSort(a);
	      break;
	    case SelectUnique:
	      return selectUnique(a);
	    }
	    return new String(a);
	  }

	  public static void main(String[] a) {
	    if ((a == null) || (a.length < 1)) {
	      a = new String[1];
	      a[0] = "the quick brown fox jumps over the lazy dog";
	    }
	    for (String s: a) {
	      System.out.println("'" + s + "' selection sorts to '" +
	                         sortChars(s, SortType.SelectionSort) + "'");
	      System.out.println("'" + s + "'    bubble sorts to '" +
	                         sortChars(s, SortType.BubbleSort) + "'");
	      System.out.println("'" + s + "' insertion sorts to '" +
	                         sortChars(s, SortType.InsertionSort) + "'");
	      System.out.println("'" + s + "' has characters '" +
	                         sortChars(s, SortType.SelectUnique) + "'");
	    }
	  }
	}
