import java.util.*;
import java.io.*;

public class Trie {
 
	class TrieNode
	{
		Map<Character,TrieNode> children;
		boolean endOfWord;
		public TrieNode()
		{
			children = new HashMap<>();
			endOfWord = false;
		}
	}
	TrieNode root;
 
	public Trie()
	{
		root=new TrieNode();
	}
 
	public void insert(String s)
	{
		insertUtil(s,0,root);
	}
 
	public void insertUtil(String s,int index,TrieNode t)
	{
		if(index>=s.length())
		{
			t.endOfWord=true;
			return;
		}
		if(!t.children.containsKey(s.charAt(index)))
			t.children.put(s.charAt(index),new TrieNode());
		insertUtil(s,index+1,t.children.get(s.charAt(index)));
	}
 
	public boolean search(String s)
	{
		return searchUtil(s,0,root);
	}
 
	public boolean searchUtil(String s,int index,TrieNode t)
	{
		if(index==s.length())
			return t.endOfWord;
		if(t.children.containsKey(s.charAt(index)))
			return searchUtil(s,index+1,t.children.get(s.charAt(index)));
		else
			return false;
	}
 
	public void delete(String s)
	{
		deleteUtil(s,0,root);
	}
 
	public boolean deleteUtil(String s,int index,TrieNode t)
	{
		if(index>=s.length()) {
			if(!t.endOfWord)
				return false;
			t.endOfWord = false;
			return t.children.isEmpty();
		}
		if(t==null)
			return false;
		boolean toDelete= deleteUtil(s,index+1,t.children.get(s.charAt(index)));
		if(toDelete)
			t.children.remove(s.charAt(index));
		return t.children.isEmpty() && !t.endOfWord;
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie t=new Trie();
		t.insert("jayesh");
		t.insert("jay");
		System.out.println(t.search("jay"));
		System.out.println(t.search("jayesh"));
		t.delete("jayesh");
		System.out.println(t.search("jay"));
		System.out.println(t.search("jayesh"));
	}
 
}