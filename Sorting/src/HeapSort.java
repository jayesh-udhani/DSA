import java.util.*;

public class HeapSort {
	public static void build_heap(int[] a)
	{
		int i,n=a.length;
		for(i=(int)(Math.floor(n/2.0)-1);i>=0;i--)
			heapify(a,n,i);
	}
	public static void heapify(int[] a,int n,int i)
	{
		int max=i,t;
		if(2*i+1<n && a[2*i+1]>a[max])
			max=2*i+1;
		if(2*i+2<n && a[2*i+2]>a[max])
			max=2*i+2;
		if(max!=i)
		{
			t=a[i];
			a[i]=a[max];
			a[max]=t;
			heapify(a,n,max);
		}
	}
	public static void sort(int[] a)
	{
		int i,t,n=a.length;
		build_heap(a);
		for(i=n-1;i>0;i--)
		{
			t=a[i];
			a[i]=a[0];
			a[0]=t;
			heapify(a,i,0);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int i,n=in.nextInt();
		int[] a=new int[n];
		for(i=0;i<n;i++)
			a[i]=in.nextInt();
		sort(a);
		for(i=0;i<n;i++)
			System.out.print(a[i]+" ");
		System.out.println();
		in.close();
	}

}
