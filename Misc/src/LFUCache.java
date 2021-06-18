/* Least Frequently Used Cache
 * Link : https://leetcode.com/problems/lfu-cache/
 * Better solutions :
 * 1) https://leetcode.com/problems/lfu-cache/discuss/94521/JAVA-O(1)-very-easy-solution-using-3-HashMaps-and-LinkedHashSet
 * 2) https://leetcode.com/problems/lfu-cache/discuss/94547/Java-O(1)-Solution-Using-Two-HashMap-and-One-DoubleLinkedList
 */

import java.util.*;
import java.io.*;

public class LFUCache {

    class Pair
    {
        int a,b;
        Pair(int a,int b)
        {
            this.a=a;
            this.b=b;
        }
        @Override
        public boolean equals(Object p)
        {
            if(this==p)
                return true;
            if(p==null || this.getClass()!=p.getClass())
                return false;
            return this.a==((Pair)p).a;
        }
        @Override
        public int hashCode()
        {
            return this.a;
        }
        @Override
        public String toString()
        {
            return this.a+" "+this.b;
        }
    }
    int size;
    Map<Integer,Pair> hm; // key: key and value : Pair (value,frequency)
    TreeMap<Integer,LinkedHashSet<Pair>> tm; 
    // key: frequency and value is LinkedHashSet of Pair (key,value)
    public LFUCache(int capacity) {
        hm=new HashMap<>();
        tm=new TreeMap<>();
        size=capacity;
    }
    
    public int get(int key) {
        if(hm.containsKey(key))
        {
            int val=hm.get(key).a,freq=hm.get(key).b;
            hm.get(key).b++;
            LinkedHashSet<Pair> set=tm.get(freq);
            set.remove(new Pair(key,val));
            if(set.isEmpty())
                tm.remove(freq);
            if(tm.containsKey(freq+1))
                tm.get(freq+1).add(new Pair(key,val));
            else
            {
                LinkedHashSet<Pair> toAdd=new LinkedHashSet<>();
                toAdd.add(new Pair(key,val));
                tm.put(freq+1,toAdd);
            }
            return val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key))
        {
            int freq=hm.get(key).b,oldVal=hm.get(key).a;
            hm.get(key).b++;
            hm.get(key).a=value;
            LinkedHashSet<Pair> set=tm.get(freq);
            set.remove(new Pair(key,oldVal));
            if(set.isEmpty())
                tm.remove(freq);
            if(tm.containsKey(freq+1))
                tm.get(freq+1).add(new Pair(key,value));
            else
            {
                LinkedHashSet<Pair> toAdd=new LinkedHashSet<>();
                toAdd.add(new Pair(key,value));
                tm.put(freq+1,toAdd);
            }
        }
        else
        {
            if(hm.size()==size)
            {
                if(size==0)
                    return;
                Map.Entry first=tm.firstEntry();
                LinkedHashSet<Pair> set=(LinkedHashSet)first.getValue();
                int keyToRemove=set.iterator().next().a;
                set.remove(set.iterator().next());
                if(set.isEmpty())
                    tm.remove((Integer)first.getKey());
                hm.remove(keyToRemove);
            }
            hm.put(key,new Pair(value,1));
            if(tm.containsKey(1))
                tm.get(1).add(new Pair(key,value));
            else
            {
                LinkedHashSet<Pair> toAdd=new LinkedHashSet<>();
                toAdd.add(new Pair(key,value));
                tm.put(1,toAdd);
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */