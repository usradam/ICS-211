package h10;

import java.util.LinkedList;
import java.util.Queue;

public class CircularArrayQueue<E> {
	
	// Instance variables for the queue
	E[] array;
	int front = 0;
	int rear = 0;
	int size = 0;
	
	
	// Constructor
	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		array = (E[]) new Object[10];
	}
	
	
	// offer() - add an element at the end of the queue (almost same as add except throw exception instead of false)
	public boolean offer(E element) {
		if(size == array.length) {
			return false;
		}
		
		array[rear] = element;
		size++;
		rear = (rear + 1) % array.length;
		return true;
	}
	
	
	// poll() - retrieve and remove an element at the front of the queue (if size == 0  then return false)
	public E poll() {
		if(size == 0) {
			return null;
		}
		
		E result = array[front];	// <--- We will return this at the end
		size--;
		front = (front + 1) % array.length;
		return result;
	}
	
	
	// peek() - retrieve an element from the front of the queue WITHOUT removing it (except return exception instead of null)
	public E peek() {
		if(size == 0) {
			return null;
		}
		
		return array[front];
	}
	
	
	

	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<String>();
		
	}

}
