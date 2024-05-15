package h06;

import java.util.Arrays;

public class OffsetArrayList<E> {

	// Instance variables
	E[] array;
	int size = 0;
	int offset;


	@SuppressWarnings("unchecked")
	// Constructor
	OffsetArrayList(int initialCapacity, int offset) {
		// Check that the initialCapacity >= 0
		if(initialCapacity < 0)
			throw new NegativeArraySizeException();

		array = (E[]) new Object[initialCapacity];
		this.offset = offset;
	}

	// 1.2 Methods and Exceptions	

	int size() {
		return size;
	}

	int capacity() {
		return array.length;
	}

	int offset() {
		return offset;
	}




	E get(int index) {
		// Check that index is within bounds of the array
		if(index < offset || index >= (size + offset)) {
			throw new IndexOutOfBoundsException();
		}

		return array[index - offset];
	}

	E set(int index, E element) {
		if(index < offset || index >= offset+size()) {
			throw new IndexOutOfBoundsException();
		}

		E oldValue = array[index - offset];
		array[index - offset] = element;
		return oldValue;
	}

	boolean add(E element) {
		if(size == array.length) {
			array = Arrays.copyOf(array, (size * 2) + 1);
		}

		array[size] = element;
		size++;

		return true;
	}

	void add(int index, E element) {
		if(index < offset || index >= offset+size() + 1) {
			throw new IndexOutOfBoundsException();
		}

		if(size == array.length) {
			array = Arrays.copyOf(array, (size * 2) + 1);
		}

		for(int i = size; i > index - offset; i--) {
			array[i + offset] = array[i - 1 + offset];
		}

		array[index - offset] = element;
		size++;
	}

	E remove(int index) {
		if(index < offset || index >= offset+size()) {
			throw new IndexOutOfBoundsException();
		}

		E result = get(index);
		for(int i = index - offset; i < size - 1; i++) {
			array[i] = array[i + 1];
		}

		size--;
		return result;

	}

	@Override
	public String toString() {
		String separator = "";
		String result = "";

		for (int i = 0; i < size; i++) {
			result += separator + array[i].toString();
			separator = ",";
		}

		return result.toString();
	}


	public static boolean unitTest() {

		for(int offset = -5; offset <= 10; offset++) {

			// Create an OffsetArrayList using he offset value of this loop
			OffsetArrayList<String> list = new OffsetArrayList<>(10, offset);

			// Test get() method with an invalid index
			try {
				list.get(offset - 1);
				return false;				// <--- This line should not be executed
			} catch(IndexOutOfBoundsException error) {}


			// Test set() with an invalid index
			try {
				list.set(offset - 1, null);
				return false;	
			} catch(IndexOutOfBoundsException error) {}

			
			// Test two-parameter add() with an invalid index
			try {
				list.add(offset - 1, null);
				return false;
			} catch(IndexOutOfBoundsException error) {}


			// Test remove() with an invalid index
			try {
				list.remove(offset - 1);
				return false;
			} catch(IndexOutOfBoundsException error) {}

		}

		// Test that the constructor throws exception with negative capacity
		try {
			OffsetArrayList<String> list = new OffsetArrayList<>(-1, 0);
			return false;
		} catch(NegativeArraySizeException error) {}

		// Test toString() with varying sizes and offsets
		String[] inputs = {"Welcome", "to", "class"};
		String[] outputs = {"", "Welcome", "Welcome,to", "Welcome,to,class"};

		for(int size = 0; size <= 3; size++) {
			for(int offset = -1; offset <= 2; offset++) {

				OffsetArrayList<String> list = new OffsetArrayList<>(10, offset);

				for(int i = 0; i < size; i++) {
					list.add(inputs[i]);
				}

				if(!list.toString().equals(outputs[size]))
					return false;
			}
		}	
		return true;
	}


	public static void main(String[] args) {
		System.out.println("---- Unit Test ----");
		System.out.println(unitTest() + "" + '\n');

		OffsetArrayList<String> list = new OffsetArrayList<>(0, 0);

		// boolean add method tests
		System.out.print("---- Boolean add method tests ----");
		System.out.println(list.toString());
		list.add("Hello");
		System.out.println(list.toString());
		list.add("World");
		System.out.println(list.toString());
		list.add("This");
		System.out.println(list.toString());
		list.add("Is");
		System.out.println(list.toString());
		list.add("Adam");
		System.out.println(list.toString());
		list.add("Taking");
		System.out.println(list.toString());
		list.add("ICS"); 
		System.out.println(list.toString());
		list.add("211");
		System.out.println(list.toString() + '\n');
		
		// remove method test
		System.out.println("---- Remove method test ----");
		list.remove(0);
		System.out.println(list.toString() + '\n');

		// void add method test
		System.out.println("---- Void add method test ----");
		list.add(2, "input test");
		System.out.println(list.toString() + '\n');
		
		// get method tests
		System.out.println("--- Get method tests ---");
		System.out.println(list.get(0));
		System.out.println(list.get(3) + '\n');
		
		// set method tests
		System.out.println("--- Set method tests ---");
		System.out.println(list.toString());
		list.set(0, "test");
		System.out.println(list.toString());
		list.set(5, "works");
		System.out.println(list.toString());
	}
}
