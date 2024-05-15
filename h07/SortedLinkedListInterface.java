package h07;

public interface SortedLinkedListInterface<E extends Comparable<E>> {
	  // List methods that are useful for a sorted linked list
	  int size();
	  E get(int index);
	  
	  
	  // return true if the value has been added, false if it was already present
	  boolean add(E value);
	  
	  
	  // return true if the value has been removed, false if it was not present
	  // uses value.compareTo to decide if the value has been found
	  boolean remove(E value);
	  
	  
	  // return the index at which the value can be found, or -1 if not found
	  // uses value.compareTo to decide if the value has been found
	  int indexOf(E value);
	  
	  
	  // return the contents with blanks (" ") in-between
	  String toString();
	}
