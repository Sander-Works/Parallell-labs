package no.hiof.itf23019.graph_search.dfs.parallel;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import no.hiof.itf23019.common_structure.tree.TreeNode;

public class ParallelDepthFirstSearch {

	public void parallelDFS(TreeNode node) {
		ConcurrentLinkedQueue<TreeNode> nodes = new ConcurrentLinkedQueue<>();
		
		node.visit();
		for(TreeNode child : node.getChildren())
			nodes.add(child);

		// Create the executor
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		// Number of task to create
		int numTasks = Runtime.getRuntime().availableProcessors();

		// Create the task and submit them to the executor
		for (int i = 0; i < numTasks; i++) {
			DepthFirstSearchTask task = new DepthFirstSearchTask(nodes);
			executor.execute(task);
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

		
	}
}
