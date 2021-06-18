/* Problem : Insert Delete GetRandom O(1) - Duplicates allowed
 * Link : https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * 
 */

import java.util.*;
import java.io.*;

public class RandomizedCollection {
	Map<Integer,Set<Integer>> hm;
    List<Integer> al;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        hm=new HashMap<>();
        al=new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        al.add(val);
        if(hm.containsKey(val))
        {
            hm.get(val).add(al.size()-1);
            return false;
        }
        else
        {
            Set<Integer> toAdd=new HashSet<>();
            toAdd.add(al.size()-1);
            hm.put(val,toAdd);
            return true;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(hm.containsKey(val))
        {
            Set<Integer> occurrences=hm.get(val);
            int toRemove=occurrences.iterator().next();
            occurrences.remove(toRemove);
            if(occurrences.isEmpty())
                hm.remove(val);
            if(al.size()-1!=toRemove)
            {
                int temp=al.get(toRemove);
                al.set(toRemove,al.get(al.size()-1));
                al.set(al.size()-1,temp);
                hm.get(al.get(toRemove)).remove(al.size()-1);
                hm.get(al.get(toRemove)).add(toRemove);
            }
            al.remove(al.size()-1);
            return true;
        }
        else
            return false;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int l=al.size();
        return al.get(new Random().nextInt(l));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */