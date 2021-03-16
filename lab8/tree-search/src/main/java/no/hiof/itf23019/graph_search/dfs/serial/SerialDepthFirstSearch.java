package no.hiof.itf23019.graph_search.dfs.serial;

import no.hiof.itf23019.common_structure.tree.TreeNode;
import no.hiof.itf23019.graph_search.dfs.parallel.DepthFirstSearchTask;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SerialDepthFirstSearch {

	public void serialDFS(TreeNode node)
	{
		if(!node.isVisited())
		{
			node.visit();
		    //System.out.print(node.getName() + " ");

			//TODO: continue the implementation of DFS
			//Hint: recursive call to serialDFS for each child node
			serialDFS(node);
			/*
			while(!nodes.isEmpty())
			{
				node = nodes.poll();

				if(node != null && !node.isVisited())
				{
					new SerialDepthFirstSearch().serialDFS(node);
				}
			}
			*/

		}
	}
}
