package h09;

import java.util.EmptyStackException;
import java.util.Stack;

public class LinkedStack {
	
	private static class LinkedNode {
	    long item;
	    LinkedNode next;
	    @SuppressWarnings("unused")
		public LinkedNode(long value) {
	      item = value;
	      next = null;
	    }
	    public LinkedNode(long value, LinkedNode rest) {
	      item = value;
	      next = rest;
	    }
	  }
	
	// Instance variables
	LinkedNode top = null;
	int size = 0;
	
	
	// push() - add new elements to the top of the stack
	public void push(long value) {
		
		// Create a new node with the value we're pushing to the stack. Its 'next' is the current top node
		LinkedNode newNode = new LinkedNode(value, top);
		
		// Reassign 'top' to our newNode
		top = newNode;
		
		// Increment 'size'
		size++;
	}
	
	// pop() - remove the element at the top of the stack and return it
	public long pop() {
		
		// Check if the stack is empty
		if(size == 0 || top == null) {
			throw new EmptyStackException();
		}
		
		// Assign the value from the top of the stack to a temp variable
		long result = top.item;
		
		// Move 'top' to the next node in the stack
		top = top.next;
		
		// Decrement 'size'
		size--;
		
		// Return the popped value
		return result;
	}
	
	
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		
		System.out.println(stack.search("Z"));
	}

}
