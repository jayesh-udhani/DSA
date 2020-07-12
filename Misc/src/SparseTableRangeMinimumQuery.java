import java.util.*;
import java.io.*;

public class SparseTableRangeMinimumQuery {

	public static int log2(int n)
	{
		return (int)(Math.log(n)/Math.log(2.0));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int i,j,n=in.nextInt();
		int[] a=new int[n];
		for(i=0;i<n;i++)
			a[i]=in.nextInt();
		int[][] dp=new int[n][log2(n)+1];
		for(j=0;j<dp[0].length;j++)
		{
			for(i=0;i+(1<<j)-1<n;i++)
			{
				if(j==0)
					dp[i][j]=i;
				else
				{
					if(a[dp[i][j-1]] < a[dp[i+(1<<(j-1))][j-1]])
						dp[i][j]=dp[i][j-1];
					else
						dp[i][j]=dp[i+(1<<(j-1))][j-1];
				}
			}
		}
		for(i=0;i<n;i++)
		{
			for(j=i;j<n;j++)
				System.out.print(rangeMinimumQuery(a,dp,i,j)+" ");
			System.out.println();
		}
		
	}
	public  static int rangeMinimumQuery(int[] a, int[][] dp, int low, int high) {
		// TODO Auto-generated method stub
		int l=high-low+1,k=log2(l);
		return Math.min(a[dp[low][k]], a[dp[low+l-(1<<k)][k]]);	
	}

}
