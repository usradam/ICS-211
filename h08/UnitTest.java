package h08;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class UnitTest {

	@Test
	void testArrayListIterator() {
		String[] elements = {"A", "B", "C", "D", "E"};
		for(int size = 0; size <= 5; size++) {
			ArrayList<String> list = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				list.add(elements[i]);

			}
			System.out.println(list);
			for(String element : list) {
				System.out.println(element);
			}		
		}
	}


	@Test
	void testICS211LinkedList() {
		String[] elements = {"A", "B", "C", "D", "E"};
		for(int size = 0; size <= 5; size++) {
			ICS211LinkedList<String> list = new ICS211LinkedList<>();
			for(int i = 0; i < size; i++) {
				list.add(elements[i]);

			}
			System.out.println(list);
			for(String element : list) {
				System.out.println(element);
			}		
		}
	}


	@Test
	void testRemoveValues() {
		ICS211LinkedList<String> list = new ICS211LinkedList<>();

		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");

		Iterator<String> iterator = list.iterator();

		// Before first call to next() exception
		try {
			iterator.remove();
		}
		catch (IllegalStateException e) {
		}

		System.out.println(list);
		assertEquals(list.toString(), "A ==> B ==> C ==> D ==> E ==> F");

		// Remove first value
		iterator.next();
		iterator.remove();
		assertEquals(5, list.size) ;
		assertEquals(list.toString(), "B ==> C ==> D ==> E ==> F");

		// Remove intermediate value
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.remove();
		try {
			iterator.remove();
		} 
		catch(IllegalStateException e){
		}
		assertEquals(4, list.size);
		assertEquals(list.toString(), "B ==> C ==> E ==> F");


		// Remove last value
		iterator.next();
		iterator.next();
		// After next() has returned last value exception
		try {
			iterator.next();
		}
		catch (NoSuchElementException e) { 
			System.out.println("Success");
		}
		iterator.remove();
		assertEquals(3, list.size);
		assertEquals(list.toString(), "B ==> C ==> E");
	}
	
	
	@Test
	void testRemoveIndices() {
		ICS211LinkedList<String> list = new ICS211LinkedList<>();

		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");

		try {
		list.remove(-1);
		assertFalse(true);
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		assert(list.remove(0) == "A");
		assertEquals("D", list.remove((list.size)/2)); // Or assert("D" == list.remove(list.size)/2);
		assert("F" == list.remove((list.size)-1));
		try {
		list.remove(list.size);
		assert(false);
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
}
