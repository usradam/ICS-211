package h08;

import java.util.ArrayList;
import java.util.Iterator;

public class ICS211ArrayListIterator<E> implements Iterator<E> {

	// Class variable
	E[] array;
	int index;
	
	// Constructor
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ICS211ArrayListIterator(java.util.ArrayList data) {
		array = (E[]) data.toArray();
		index = 0;
	}

	@Override
	public boolean hasNext() {
		if(index < array.length)
			return true;
		else
			return false;
	}

	@Override
	public E next() {
		E result = array[index];
		index++;
		return result;
	}
	
	
	
	
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		
		ICS211ArrayListIterator<String> it = new ICS211ArrayListIterator<>(list);
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
