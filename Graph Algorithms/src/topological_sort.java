import java.util.*;
import java.io.*;

public class topological_sort {
	public static class Graph
	{
		private int v;
		private List<List<Integer>> al;
		Graph(int v)
		{
			this.v=v;
			al=new ArrayList<>();
			for(int i=0;i<v;i++)
				al.add(new ArrayList<Integer>());
		}
		public void addEdge(int p,int q)
		{
			al.get(p).add(q);
		}
		public void topologicalSort()
		{
			Set<Integer> visited=new HashSet<>();
			Stack<Integer> st=new Stack<>();
			for(int i=0;i<v;i++)
			{
				if(!visited.contains(i))
					topSortUtil(i,st,visited);
			}
			System.out.println("Topological sorting of the graph is ");
			while(!st.isEmpty())
				System.out.print(st.pop()+" ");
		}
		public void topSortUtil(int i,Stack<Integer> st,Set<Integer> visited)
		{
			visited.add(i);
			for(int j:al.get(i))
			{
				if(!visited.contains(j))
					topSortUtil(j,st,visited);
			}
			st.push(i);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int i,n=in.nextInt(),e=in.nextInt();
		Graph g=new Graph(n);
		for(i=0;i<e;i++)
			g.addEdge(in.nextInt(), in.nextInt());
		g.topologicalSort();
		in.close();
	}

}
