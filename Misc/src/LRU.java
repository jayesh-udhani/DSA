// Problem statement : https://leetcode.com/problems/lru-cache/
import java.util.*;


public class LRU {
	LinkedHashMap<Integer,Integer> hm;
    int size;
    public LRU(int capacity) {
        hm=new LinkedHashMap<>();
        size=capacity;
    }
    
    public int get(int key) {
        if(hm.containsKey(key))
        {
            int val=hm.remove(key);
            hm.put(key,val);
            return val;
        }
        else
            return -1;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key))
            hm.remove(key);
        hm.put(key,value);
        if(hm.size()>size)
            hm.remove(hm.entrySet().iterator().next().getKey());
    }
}
