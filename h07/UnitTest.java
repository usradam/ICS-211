package h07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UnitTest {

	@Test
	void testStringLists() {

		SortedLinkedList<String> list = new SortedLinkedList<>();


		// TODO: Test add()
		assert(list.add("Hello") == true);
		assert(list.add("World") == true);
		assert(list.add("Hello") == false);

		
		// TODO: Test size()
		assert(list.size() == 2);
		list.add("New element");
		assert(list.size() == 3);


		// TODO: Test get()
		assertEquals("Hello", list.get(0));
		assertEquals("New element", list.get(1));
		assertEquals("World", list.get(2));
		assertNull(list.get(3));


		// TODO: Test indexOf()
		assertEquals(0, list.indexOf("Hello"));
		assertEquals(2, list.indexOf("World"));
		assertEquals(1, list.indexOf("New element"));
		assertEquals(-1, list.indexOf("Null"));
		

		// TODO: Test toString()
		assertEquals("Hello New element World", list.toString());
		list.add("Test");
		list.add("A");
		list.add("String");
		list.add("For");
		list.add("Linked");
		list.add("Sorted");
		list.add("List");
		list.add("Random words");
		assertEquals("A For Hello Linked List New element Random words Sorted String Test World", 
		list.toString());


		// TODO: Test remove()
		assertFalse(list.remove("World"));
		assertFalse(list.remove("Null"));
		assert(!(list.size() == 2));

	}


	@Test
	void testIntegerLists() {

		SortedLinkedList<Integer> list = new SortedLinkedList<>();


		// TODO: Test add()
		assert(list.add(10) == true);
		assert(list.add(4) == true);
		assert(list.add(10) == false);

		
		// TODO: Test size()
		assert(list.size() == 2);
		list.add(777);
		assert(list.size() == 3);

		
		// TODO: Test get()
		assertEquals(4, list.get(0));
		assertEquals(10, list.get(1));
		assertEquals(777, list.get(2));
		assertNull(list.get(12));


		// TODO: Test indexOf()
		assertEquals(1, list.indexOf(10));
	    assertEquals(0, list.indexOf(4));
	    assertEquals(2, list.indexOf(777));
	    assertEquals(-1, list.indexOf(9999));


		// TODO: Test toString()
	    assertEquals("4 10 777", list.toString());
	    
	    
		// TODO: Test remove()
	    assertTrue(list.remove(10));
	    assertFalse(list.remove(9999));
	    assertEquals(2, list.size());
	    assertEquals("4 777", list.toString());

	}

}
