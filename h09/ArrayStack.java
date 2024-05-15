package h09;

import java.util.EmptyStackException;

public class ArrayStack {
	
	// Instance variables
	long[] array;
	int top = -1;
	int size = 0;
	
	// Constructor
	public ArrayStack() {
		array = new long[10];
	}
	
	
	// push() - add new elements to the top of the stack
	public void push(long value) {
		
		// Check to see if the array is full
		if(size == array.length) {
			throw new IndexOutOfBoundsException();
		}
		
		// Increment 'top'
		top++;
		
		// Insert new element in the array index that 'top' refers to
		array[top] = value;
		
		// Increase the size of the stack
		size++;
	}
	
	
	
	// pop() - remove the element at the top of the stack and return it
	public long pop() {
		
		// Check to see if the stack is empty
		if(size == 0 && top == -1) {
			throw new EmptyStackException();
		}
		
		// Store value at the top of the stack to a temporary variable
		long result = array[top];
		
		// Decrement 'top'
		top--;
		
		// Decrement our size
		size--;
		
		// Return the remove value
		return result;
	}

}
