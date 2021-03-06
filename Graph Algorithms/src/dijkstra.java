import java.io.*;
import java.util.*;

public class dijkstra {
/* Given simple undirected weighted graph
 * Input : n (number of vertices), number of edges e and source vertex s
 * Vertices are numbered from 0 to n-1
 * edges are given in the form of 2D array i.e. int[][] edges=new int[][3] with 
 * There exists an edge between vertices edges[i][0] and edges[i][1] with weight edges[i][2]
 * Input to test
 *  6 7 0
	0 1 5
	1 2 2
	2 3 3
	0 3 9
	0 4 2
	3 5 2
	4 5 3
 */
	static int n;
	static class Pair
	{
		int x,y;
		Pair(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	public static class heapNMap
	{
		Map<Integer,Integer> hm;
		Pair[] a;
		int size;
		public heapNMap()
		{
			hm=new HashMap<>();
			a=new Pair[n];
			size=0;
		}
		public boolean isEmpty()
		{
			return hm.isEmpty();
		}
		public void add(int vertex,int dist)
		{
			a[size]=new Pair(vertex,dist);
			hm.put(vertex, size);
			int i=size;
			while(i>0 && a[i].y<a[(i-1)/2].y)
			{
				hm.put(a[i].x, (i-1)/2);
				hm.put(a[(i-1)/2].x, i);
				Pair t=a[i];
				a[i]=new Pair(a[(i-1)/2].x,a[(i-1)/2].y);
				a[(i-1)/2]=t;
				i=(i-1)/2;
			}
			size++;
		}
		public boolean contains(int vertex)
		{
			return hm.containsKey(vertex);
		}
		public Pair extractMin()
		{
			Pair p=a[0];
			size--;
			if(size!=0)
			{
				a[0]=new Pair(a[size].x,a[size].y);
				hm.put(a[size].x, 0);
				heapify(0);
			}
			hm.remove(p.x);
			return p;
		}
		public void heapify(int i)
		{
			int smallest=i;
			if(2*i+1<size && a[2*i+1].y<a[smallest].y)
				smallest=2*i+1;
			if(2*i+2<size && a[2*i+2].y<a[smallest].y)
				smallest=2*i+2;
			if(smallest!=i)
			{
				hm.put(a[smallest].x, i);
				hm.put(a[i].x, smallest);
				Pair t=a[i];
				a[i]=new Pair(a[smallest].x,a[smallest].y);
				a[smallest]=t;
				heapify(smallest);
			}
		}
		public void decreaseKey(int vertex,int dist)
		{
			if(!hm.containsKey(vertex))
				return;
			int index=hm.get(vertex);
			if(a[index].y<=dist)
				return;
			a[index].y=dist;
			while(index>0 && a[index].y<a[(index-1)/2].y)
			{
				hm.put(a[index].x, (index-1)/2);
				hm.put(a[(index-1)/2].x, index);
				Pair t=a[index];
				a[index]=new Pair(a[(index-1)/2].x,a[(index-1)/2].y);
				a[(index-1)/2]=t;
				index=(index-1)/2;
			}
		}
		public int getDist(int vertex)
		{
			return a[hm.get(vertex)].y;
		}
	}
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		int i,e=in.nextInt(),s=in.nextInt(),max=(int)1e6;
		heapNMap ds=new heapNMap();
		int[][] edges=new int[e][3];
		List<List<Pair>> al=new ArrayList<>();
		for(i=0;i<n;i++)
			al.add(new ArrayList<Pair>());
		for(i=0;i<e;i++)
		{
			edges[i][0]=in.nextInt();
			edges[i][1]=in.nextInt();
			edges[i][2]=in.nextInt();
			al.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
			al.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
		}
		for(i=0;i<n;i++)
			ds.add(i,max);
		ds.decreaseKey(s, 0);
		Map<Integer,Integer> parent,dist;
		parent=new HashMap<>();
		dist=new HashMap<>();
		parent.put(s, -1);
		while(!ds.isEmpty())
		{
			Pair p=ds.extractMin();
			dist.put(p.x, p.y);
			for(i=0;i<al.get(p.x).size();i++)
			{
				if(ds.contains(al.get(p.x).get(i).x) && ds.getDist(al.get(p.x).get(i).x)>p.y+al.get(p.x).get(i).y)
				{
					ds.decreaseKey(al.get(p.x).get(i).x, p.y+al.get(p.x).get(i).y);
					parent.put(al.get(p.x).get(i).x, p.x);
				}
			}
		}
		for(i=0;i<n;i++)
			System.out.println("Distance of "+i+" from "+s+" is "+dist.get(i));
		System.out.println("Printing path from source vertex to every other vertex");
		for(i=0;i<n;i++)
			printPath(parent, s, i);
		in.close();
	}
	public static void printPath(Map<Integer,Integer> parent,int source,int vertex)
	{
		Stack<Integer> st=new Stack<>();
		int i=vertex;
		st.push(vertex);
		while(i!=source)
		{
			i=parent.get(i);
			st.push(i);
		}
		while(!st.isEmpty())
		{
			System.out.print(st.pop());
			if(!st.isEmpty())
				System.out.print("-");
		}
		System.out.println();
	}
}
