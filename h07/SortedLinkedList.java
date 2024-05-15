package h07;

public class SortedLinkedList<E extends Comparable<E>> implements SortedLinkedListInterface<E> {

	// Sorted Linked List variables
	LinkedNode<E> head;
	LinkedNode<E> tail;
	int size;

	// Constructor
	public SortedLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public SortedLinkedList(E value) {
		LinkedNode<E> newNode = new LinkedNode(value);
		head = newNode;
		tail = newNode;
		size = 1;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public E get(int index) {
		// Check for invalid indices
		if(index < 0 || index >= size) {
			return null;
		}

		LinkedNode<E> it = head;
		for(int i = 0; i < index; i++) {
			it = it.next;
		}

		return it.value;
	}

	@Override
	public boolean add(E value) {

		// Case 1: Empty list (New element is both head and tail)
		if(size == 0) {
		LinkedNode<E> newNode = new LinkedNode<>(value);
		head = newNode;
		tail = newNode;
		size++;
		return true;
		}

		// Case 2: List of size 1 (New element is either head or tail)
		if(size == 1) {
		LinkedNode<E> newNode = new LinkedNode<>(value);
		if(value.compareTo(head.value) < 0) {
		newNode.next = head;
		head = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		
		size++;
		return true;
		}
		

		// Case 3: New element inserted before head (update head)
		if(value.compareTo(head.value) < 0) {
			LinkedNode<E> newNode = new LinkedNode<>(value);
			newNode.next = head;
			head = newNode;
			size++;
			return true;
		}


		// Case 4: New element inserted after tail (update tail)
		if(value.compareTo(tail.value) > 0) {
			LinkedNode<E> newNode = new LinkedNode<>(value);
			tail.next = newNode;
			tail = newNode;
			size++;
			return true;
		}


		// Default: Adding new element somewhere in the middle of the list
	
			// Check if new value is duplicate of head node
			if(value.compareTo(head.value) == 0)
				return false;

			LinkedNode<E> it = head;
			while(it.next.value.compareTo(value) < 0) {
				it = it.next;
			}
			if(it.next.value.compareTo(value) == 0)
				return false;

			LinkedNode<E> newNode = new LinkedNode<>(value);
			newNode.next = it.next;
			it.next = newNode;
			size++;
			return true;
		}


	@Override
	public boolean remove(E value) {

		if(size == 0) {
			return false;
		}
		
		if(value.compareTo(head.value) == 0) {
			head = head.next;
			size--;
			if(head == null) {
				tail = tail.next;
			}
			return true;
		}
		
		LinkedNode<E> it = head;
		for(int i = 0; i < size; i++) {
			if(it.next == null) {
				return false;
			}
			
			if(it.next.value.compareTo(value) == 0) {
				it.next = it.next.next;
				if(it.next == null) {
					tail = it;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(E value) {
		LinkedNode<E> it = head;
		for(int i = 0; i < size; i++) {
			
			// Compare the value at the current node (it.value) with the target value
			if(it.value.compareTo(value) == 0) {
				
				// If they match, you return the current index
				return i;
			}
			// If they don’t match, move to the next node with it = it.next
			// Continues until it checks all nodes or found a match
			it = it.next;
		}
		// If the element is not in the list, the method will check all nodes
		// and not find a match. It will then return -1
		return -1;

	}

	@Override
	public String toString() {
		String result = "";
		LinkedNode<E> it = head;
		while(it != null) {
			result += it.value;
			if(it.next != null) {
				result += " ";
			}
			it = it.next;
		}
		return result;
	}



	@SuppressWarnings("hiding")
	private class LinkedNode<E> {

		// Linked Node variable
		E value;
		LinkedNode<E> next;

		// Linked Node Constructors
		@SuppressWarnings("unused")
		LinkedNode(E value, LinkedNode<E> next) {
			this.value = value;
			this.next = next;
		}

		LinkedNode(E value) {
			this.value = value;
			this.next = null;
		}
	}
}
