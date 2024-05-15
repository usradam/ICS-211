package h12;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implement a Hashtable (using separate chaining) from this skeleton.
 * You will need to determine what hash function to use, you can look at
 * "https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()" for a way to get an index
 * from any object in java.
 *
 * Email Benjynstrauss@gmail.com or bnstraus@hawaii.edu if you have questions
 * 
 * @author Adam Graham
 *
 * @param <K> Key of the hash table
 * @param <V> Values of the hash table
 */

public class UHM_Hashtable<K,V> {

	// required: your hash table must be an array of lists
	// required: your hash table must be called table
	// required: table must be a protected variable
	protected LinkedList<Pair<K, V>>[] table;
	int size = 0;
	double resizeThreshold = 0.75;

	@SuppressWarnings("unchecked")
	public UHM_Hashtable() {
		//TODO constructor stub
		table = new LinkedList[10];
		for(int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Pair<K, V>>();
		}
	}

	/**
	 * 
	 * @param initialCapacity: initial size of array
	 */
	public UHM_Hashtable(int initialCapacity) {
		//TODO constructor stub
		table = new LinkedList[initialCapacity];
		for(int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Pair<K, V>>();
		}
	}

	/**
	 * 
	 * @param loadFactorOnResize when the load factor exceeds this, resize
	 */
	public UHM_Hashtable(double loadFactorOnResize) {
		//TODO constructor stub
		table = new LinkedList[10];
		for(int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Pair<K, V>>();
		}
		if(loadFactorOnResize > 0)
			resizeThreshold = loadFactorOnResize;
	}

	/**
	 * 
	 * @param initialCapacity: initial size of array	
	 * @param loadFactorOnResize when the load factor exceeds this, resize
	 */
	public UHM_Hashtable(int initialCapacity, double loadFactorOnResize) {
		//TODO constructor stub
		table = new LinkedList[initialCapacity];
		for(int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<Pair<K, V>>();
		}
		if(loadFactorOnResize > 0)
			resizeThreshold = loadFactorOnResize;
	}

	public void put(K key, V value) {
		//TODO puts (adds) a key, value pair into the hash table
		// If the key does NOT exist, add a new Pair with the key:value into the table
		// If the key DOES exist, overwrite the value associated with the key
		if(loadFactor() > resizeThreshold)
			rehash(table.length * 2 + 1);

		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % table.length;
		LinkedList<Pair<K, V>> list = table[index];

		for(Pair<K, V> element : list) {
			if(key.equals(element.x)) {		// Key already exists. Update its value
				element.y = value;
				return;
			}
		}
		// Key does not exist. Add a new Pair with the key:value to the LinkedList
		list.add(new Pair(key, value));
		size++;
	}

	public V get(K key) {
		//TODO get the value associated with key K
		// If the key exists, return its associated value
		// If the key does NOT exist, return null
		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % table.length;
		LinkedList<Pair<K, V>> list = table[index];

		for(Pair<K, V> element : list) {
			if(element.x.equals(key)) {
				return element.y;
			}
		}
		return null;
	}

	public V replace(K key, V value) {
		//TODO puts a key, value pair into the hash table
		// If there was a value already at the given key, return it, else return null
		// If the key exists, replace its value and return its old value
		// If the key does not exist, return null
		if(loadFactor() > resizeThreshold)
			rehash(table.length * 2 + 1);

		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % table.length;
		LinkedList<Pair<K, V>> list = table[index];

		for(Pair<K, V> element : list) {
			if(element.x.equals(key)) {
				V oldValue = element.y;
				element.y = value;
				return oldValue;
			}
		}
	return null;
}

public V remove(K key) {
	//TODO removes a key-value pair from the hash table
	//returns the value if any
	if(loadFactor() > resizeThreshold)
		rehash(table.length * 2 + 1);

	int hashCode = Math.abs(key.hashCode());
	int index = hashCode % table.length;
	LinkedList<Pair<K, V>> list = table[index];

	Iterator<Pair<K, V>> iterator = list.iterator();
	while(iterator.hasNext()) {
		Pair<K, V> element = iterator.next();
		if(element.x.equals(key)) {
			V value = element.y;
			iterator.remove();
			size--;
			return value;
		}
	}
	return null;
}

public int size() {
	//TODO return number of elements in the hash table
	return size;
}

public void clear() {
	//TODO remove all elements from the hash table
	table = new LinkedList[10];
	for(int i = 0; i < table.length; i++) {
		table[i] = new LinkedList<Pair<K, V>>();
	}
	size = 0;
}

public double loadFactor() {
	//TODO get loadfactor
	return size / table.length;
}

public void rehash(int size) {
	// TODO rehashes the hash table, setting the size of
	// the array equal to the parameter
	LinkedList<Pair<K, V>>[] newTable = new LinkedList[size];
	for(int i = 0; i < newTable.length; i++) {
		newTable[i] = new LinkedList<Pair<K, V>>();
	}

	for(LinkedList<Pair<K, V>> list : table) {
		for(Pair<K, V> element : list) {
			int hashCode = Math.abs(element.x.hashCode());
			int index = hashCode % newTable.length;
			LinkedList<Pair<K, V>> newList = newTable[index];

			newList.add(element);
		}
	}
	table = newTable;
}

public int maxListLen() {
	//TODO returns the maximum length of any list
	int maxLength = 0;
	for(LinkedList<Pair<K, V>> list : table) {
		int length = list.size();
		if(length > maxLength) {
			maxLength = length;
		}
	}
	return maxLength;
}

/** Do Not Change - Used in grading */
public int keysAtIndex(int index) { return table[index].size(); }

/** Do Not Change - Used in grading */
public int arrayLen() { return table.length; }
}