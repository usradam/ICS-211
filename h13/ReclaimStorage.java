package h13;

import java.util.HashSet;

/* the method reclaimUnreachable traverses the graph of objects
 * beginning at root, marking reachable objects as being in use.
 * After this traversal, reclaimUnreachable returns a set of all the
 * objects that are not marked as being in use. */

public class ReclaimStorage {

  public static java.util.Set<Integer>
    reclaimUnreachable(StorageObject[] objects, int root) {
	  // STEP 1: unmark all the storage objects
	  for(StorageObject obj : objects) {
		  obj.clearInUse();
	  }
	  
	  // STEP 2: traverse the graph beginning from objects[root] and mark all
	  // reachable storage objects
	  markUsed(objects[root]);
	  
	  
	  // STEP 3: return the indices of the objects that are NOT inUse
	  HashSet<Integer> unreachable = new HashSet<Integer>();
	  for(int i = 0; i < objects.length; i++) {
		  if(objects[i].isInUse() == false)
			  unreachable.add(i);
	  }

    return unreachable;
  }
  
  
  // Recursive helper method to mark all reachable objects from the argument
  private static void markUsed(StorageObject obj) {
	  // Base case
	  if(obj.isInUse()) 
		  return;
	  
	  // Mark this object as 'inUse'
	  obj.markInUse();
	  
	  // Recursively visit all of its linked objects and do the same
	  int numLinks = obj.numLinks();
	  for(int i = 0; i < numLinks; i++) {
		  StorageObject link = obj.getLink(i);
		  markUsed(link);
	  }
	  
  }

  public static void main(String[] unused) {
    // initial unit test: so1 and so2 refer to each other, so3 refers to so1,
    // nobody refers to so4
    java.util.HashSet<StorageObject> set1 = new java.util.HashSet<>();
    java.util.HashSet<StorageObject> set2 = new java.util.HashSet<>();
    StorageObject so1 = new StorageObject("so1", 1);
    set1.add(so1);
    StorageObject so2 = new StorageObject("so2", set1);   // so2 refers to so1
    so1.setLink(0, so2);    // now so1 also refers to so2
    set1.add(so2);
    StorageObject so3 = new StorageObject("so3", set1);   // so3 -> so2 and so1
    StorageObject so4 = new StorageObject("so4", set1);   // as does so4
    StorageObject[] storageObjects = { so1, so2, so3, so4 };  // so3 @ index 2
    set1.add(so3);   // set1 has so1, so2, so3
    set2.add(so4);   // set2 only has so4
    // traversing the graph beginning with so3 should mark so3, so1, and so2,
    // and leave so4 not inUse
    System.out.println("set1 is " + set1);
    System.out.println("set2 is " + set2);
    System.out.println("reclaiming " + reclaimUnreachable(storageObjects, 0));
    assert (set2.equals(reclaimUnreachable(storageObjects, 0)));
    // TODO: additional unit tests
   
  }
}

