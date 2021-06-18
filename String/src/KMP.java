// Knuth Morris Pratt substring search algorithm

import java.util.*;
import java.io.*;

public class KMP {
	public static List<Integer> findSubstrings(String text,String pattern)
	{
		List<Integer> res=new ArrayList<>();
		int[] dp=preprocess(pattern);
		int i=0,j=0,n=text.length(),m=pattern.length();
		while(i<n)
		{
			while(i<n && j<m && text.charAt(i)==pattern.charAt(j))
			{
				i++;
				j++;
			}
			if(j==m)
				res.add(i-m);
			if(j>0)
				j=dp[j-1];
			else
				i++;
		}
		return res;
	}
	public static int[] preprocess(String pat)
	{
		int i=0,j=1,n=pat.length();
		int[] dp=new int[n];
		if(n>0)
			dp[0]=0;
		while(j<n)
		{
			if(pat.charAt(i)==pat.charAt(j))
			{
				dp[j]=i+1;
				i++;
				j++;
			}
			else
			{
				while(i>0 && pat.charAt(i)!=pat.charAt(j))
					i=dp[i-1];
				if(i==0 && pat.charAt(i)!=pat.charAt(j))
					dp[j++]=0;
			}
		}
		return dp;
	}
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		String text=in.next(),pat=in.next();
		List<Integer> substrings = findSubstrings(text,pat);
		for(int i=0;i<substrings.size();i++)
			System.out.print(substrings.get(i)+" ");
		in.close();
	}
}
