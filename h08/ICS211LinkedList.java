package h08;

import java.util.Iterator;

/** 
 * A list implemented using a singly-linked list
 * @author         Edo Biagioni
 * @lecture        ICS 211 Jan 27 (or later)
 * @date           January 26, 2011
 */

public class ICS211LinkedList<E> implements Iterable<E> {

	// here, include the LinkedNode definition

	/** 
	 * A node in a singly-linked list
	 * @author         Edo Biagioni
	 * @lecture        ICS 211 Jan 27 or later
	 * @date           January 26, 2010
	 */

	private static class LinkedNode<T> {
		private T item;
		private LinkedNode<T> next;


		/** 
		 * constructor to build a node with no successor
		 * @param the value to be stored by this node
		 */
		private LinkedNode(T value) {
			item = value;
			next = null;
		}


		/** 
		 * constructor to build a node with specified (maybe null) successor
		 * @param the value to be stored by this node
		 * @param the next field for this node
		 */
		private LinkedNode(T value, LinkedNode<T> reference) {
			item = value;
			next = reference;
		}
	}
	// end of the LinkedNode definition


	// this is the start of the linked list.  If the list is empty, it is null
	protected LinkedNode<E> head;
	// this is the end of the linked list.  If the list is empty, it is null
	protected LinkedNode<E> tail;
	protected int size;


	// there are some relationships between the class variables.  This
	// method checks that these relationships always hold.  Any
	// property that always holds is called an invariant.

	// the property may not hold in the middle of a method,
	// so only call this at the beginning or end of a public method.


	/** 
	 * checks assertion
	 * @throws java.lang.AssertionError if the assertion is not true
	 */
	private void verify(boolean mustBeTrue) {
		if (! mustBeTrue) {
			throw new java.lang.AssertionError("assertion error");
		}
	}


	/** 
	 * checks class invariants
	 * @throws java.lang.AssertionError if the invariant is violated
	 */
	private void checkInvariants() {
		// uncomment the next line to skip the checks:
		// return;
		// either head and tail are both null, or neither is null.
		// size is zero if and only if they are null, and otherwise is positive
		verify((head == null) == (tail == null));
		verify((size == 0) == (head == null));
		verify(size >= 0);
		// if the list only has one element, head should be the same as tail
		// (and also if the list has no elements), otherwise they should differ
		verify((head == tail) == (size <= 1));
		// a non-null tail variable should always have a null "next" field
		verify((tail == null) || (tail.next == null));
		// check to make sure size is the same as the length of the list.
		// this code takes O(n), so comment it out if performance is important
		int measuredSize = 0;
		LinkedNode<E> node = head;
		// if visitedLast is null, the list is empty, and tail should also be null
		LinkedNode<E> visitedLast = null;
		while (node != null) {
			visitedLast = node;
			node = node.next;
			measuredSize++;
		}
		verify(measuredSize == size);
		// also make sure "last" really is the last node in the linked list
		verify(visitedLast == tail);
	}


	/** 
	 * initializes an empty linked list
	 */
	public ICS211LinkedList() {
		head = null;
		tail = null;
		size = 0;
		// one of the constructor's jobs is to make sure that the invariants hold.
		checkInvariants();
	}



	// these private (helper) methods simplify implementation of
	// the public "add" methods
	// the helper methods never modify "size", the public methods
	// take care of that, so the invariants probably do not hold at the end of
	// a helper methods

	/** 
	 * adds at the head of the list
	 * @param the value to be added
	 */
	private void addAtFront(E value) {
		head = new LinkedNode<E>(value, head);
		if (tail == null) {
			tail = head;
		}
	}


	/** 
	 * adds at the tail of the list.  Assumes (and checks) that tail is not null
	 * @param the value to be added
	 * @throws RuntimeException
	 */
	private void addAtEnd(E value) {
		if (tail == null) {
			throw new RuntimeException ("invalid call to addAtEnd, tail is null");
		}
		LinkedNode<E> newNode = new LinkedNode<E>(value);
		tail.next = newNode;
		tail = newNode;
	}


	/** 
	 * adds a value to the list after the given node 
	 * @param the node after which the new value is added
	 * @param the value to be added
	 */
	private void addAfter(LinkedNode<E> reference, E value) {
		LinkedNode<E> newNode = new LinkedNode<E>(value, reference.next);
		reference.next = newNode;
		if (reference == tail) {  // if added at end, update tail value
			tail = newNode;
		}
	}


	/** 
	 * adds a value to the end of the list
	 * @param the value to be added
	 * @return true (the add always succeeds)
	 */
	public boolean add(E value) {
		checkInvariants();  // useful for debugging
		if (head != null) {
			addAtEnd (value);
		} else {
			addAtFront (value);
		}
		size++;
		checkInvariants();  // invariants valid at start, are they still valid?
		// i.e., did this method break the invariants?
		return true;
	}


	/** 
	 * returns the node at the requested position, may take time O(n)
	 * @param the position of the requested node, 0 for the head node
	 * @return the requested node
	 * @throws NullPointerException if the index is larger than the linked list
	 */
	private LinkedNode<E> nodeAtPosition(int index) {
		verify (index >= 0);
		LinkedNode<E> result = head;
		while (index > 0) {
			result = result.next;
			index--;
		}
		verify (result != null);
		return result;
	}


	/** 
	 * adds a value to the list, in the given position
	 * @param the position at which to add: 0 to add at the start
	 * @param the value to be added
	 * @throws IndexOutOfBoundsException if the index is less than 0
	 *         or greater than the number of elements in the linked list
	 */
	public void add(int index, E value) {
		checkInvariants();
		if ((index < 0) || (index > size)) {
			String badIndex =
					new String ("index " + index + " must be between 0 and " + size);
			throw new IndexOutOfBoundsException(badIndex);
		}
		if (index == 0) {
			addAtFront (value);
		} else {
			addAfter (nodeAtPosition (index - 1), value);
		}
		size++;
		checkInvariants();
	}


	/** 
	 * concatenates the elements of the linked list, separated by " ==> "
	 * @return the string representation of the list
	 */
	public String toString() {
		checkInvariants();
		LinkedNode<E> node = head;
		StringBuffer result = new StringBuffer();
		while (node != null) {
			result.append (node.item.toString());
			node = node.next;
			if (node != null) {
				result.append (" ==> ");
			}
		}
		checkInvariants();   // make sure we didn't break anything
		return result.toString();
	}


	public E remove(int index) { //TODO: 2.1 LinkedList remove
		checkInvariants();

		// Checking if index is invalid or there is an empty list (check boundaries)
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// Remove the head value
		if(index == 0) {
			E item = head.item;
			head = head.next;
			size--;

			// Checking if list is empty. If so, update tail to null
			if(size == 0) {
				tail = null;
			}
			return item;

		}
		// Access the node preceding the node to be removed
		LinkedNode<E> node = head;
		for(int i = 0; i < index - 1; i++) {
			node = node.next;
		}

		// Remove preceding node
		E item = node.next.item;
		node.next = node.next.next;

		// If last node is removed then update the tail
		if(index == size - 1) {
			tail = node;
		}

		size--;
		checkInvariants();
		return item;		// <-- Change so it doesn't return null
	}

	@Override
	public Iterator<E> iterator() {
		checkInvariants();
		LinkedListIterator it = new LinkedListIterator();
		checkInvariants();
		return it;
	}


	/** 
	 * A linked list iterator that does not support remove
	 * @author         Edo Biagioni
	 * @lecture        ICS 211 Feb 3 (or later)
	 * @date           February 1, 2011
	 */

	private class LinkedListIterator implements java.util.Iterator<E> {
		private LinkedNode<E> current;
		private LinkedNode<E> previous;

		private LinkedListIterator() {
			current = head;  // head is declared in the enclosing class
			previous = null;
		}

		public boolean hasNext() {
			return (current != null);
		}

		public E next() {
			if (hasNext()) {
				E result = current.item;
				if(current == head) {
					current = current.next; // may be null
				}
				
				else if(current == head.next) {
					current = current.next;
					previous = head;
				}
				
				else if(current == previous.next) {
					current = current.next;
				}
				
				else {
					current = current.next;
					previous = previous.next;
				}
				
				return result;
			}  // else: no next element
			throw new java.util.NoSuchElementException("linked list.next");
		}

		public void remove() { // TODO: LinkedListIterator remove

			// Case 1: remove() is called before first call of next()
			if(previous == null && current == head) {
				throw new IllegalStateException();
			}


			// Case 1.5: remove() is called on only element
			if(current == null && previous == head) {
				head = null;
				tail = null;
				size--; // don't forget to decrement size
			}


			// Case 2: remove() is called two times in a row (without a previous next() call)

			else if(previous != null && previous.next == current) {
				throw new IllegalStateException();
			}


			// Case 3: remove() is called for the head node (change your head variable)
			else if(current == head.next) {
				head = head.next;
				previous = null;
				size--;
			}

			// Case 4: remove() is called for the tail node (change your tail variable)
			else if(tail.next == current) {
				tail = previous;
				tail.next = null;
				size--;
			}

			// Case 5: remove() is called for some node in middle of the list
			else{
				previous.next = current;
				size--;
			}


			// To be aware of:
			// it.next ()
			// it.remove()
			// it.next()
			// it.remove()	<--- Error prone

		}

	}



	/** 
	 * unit test method -- basic testing of the functionality
	 * @param required, ignored
	 */
	public static void main (String [] arguments) {
		ICS211LinkedList<String> ll = new ICS211LinkedList<String>();
		System.out.println (ll);
		ll.add ("foo");
		System.out.println (ll);
		ll.add (1, "bar");
		System.out.println (ll);
		ll.add ("baz");
		System.out.println (ll);
		ll.add (0, "hello");
		System.out.println (ll);
		ll.add (1, "world");
		System.out.println (ll);
	}

}
