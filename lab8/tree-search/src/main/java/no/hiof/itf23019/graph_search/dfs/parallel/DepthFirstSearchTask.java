package no.hiof.itf23019.graph_search.dfs.parallel;

import java.util.concurrent.ConcurrentLinkedQueue;

import no.hiof.itf23019.common_structure.tree.TreeNode;
import no.hiof.itf23019.graph_search.dfs.serial.SerialDepthFirstSearch;

public class DepthFirstSearchTask implements Runnable{

	
	private ConcurrentLinkedQueue<TreeNode> nodes;
	
	
	public DepthFirstSearchTask(ConcurrentLinkedQueue<TreeNode> nodes) {
		this.nodes = nodes;
	}
	@Override
	public void run() {
		while(!this.nodes.isEmpty())
		{
			TreeNode node = nodes.poll();
			
			if(node != null && !node.isVisited())
			{
				new SerialDepthFirstSearch().serialDFS(node);
			}
			
		}
		
	}

}
