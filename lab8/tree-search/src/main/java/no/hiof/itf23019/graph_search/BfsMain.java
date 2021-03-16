package no.hiof.itf23019.graph_search;

import no.hiof.itf23019.common_structure.tree.Tree;
import no.hiof.itf23019.common_structure.utils.Utils;
import no.hiof.itf23019.graph_search.bfs.parallel.ParallelBreadthFirstSearch;
import no.hiof.itf23019.graph_search.bfs.serial.SerialBreadthFirstSearch;


public class BfsMain {

	public static void main(String[] args) {
		 long startTime, endTime, serRunTime=0, parRunTime=0;
		 SerialBreadthFirstSearch serialBreathFirstSearch = new SerialBreadthFirstSearch();
		 ParallelBreadthFirstSearch parallelBreadthFirstSearch = new ParallelBreadthFirstSearch();

		System.out.println("Generating tree...");
		 Tree tree = Utils.generateTree(10, 10_000_000);

		 for(int run = 1; run <= 10; run++)
		 {
			 System.out.println("RUN #" + run);
			 
			 System.out.println("Running SerialDFS...");
			 startTime = System.currentTimeMillis();
			 serialBreathFirstSearch.serialBFS(tree.getRoot());
			 endTime = System.currentTimeMillis();
			 serRunTime += endTime - startTime;
			 System.out.println("Serial version took " + (endTime - startTime) + " miliseconds");
			 
			//Clear the visit flag
			 tree.unvisit();
			 
			 //TODO: Implement parallel BFS here
			 //Hint: Refer to the DfsMain.java
			 System.out.println("Running ParallelBFS...");
			 startTime = System.currentTimeMillis();
			 parallelBreadthFirstSearch.parallelBFS(tree.getRoot());
			 endTime = System.currentTimeMillis();
			 parRunTime += endTime - startTime;
			 System.out.println("Parallel version took " + (endTime - startTime) + " miliseconds");

			 //Clear the visit flag
			 tree.unvisit();
		 }
		
		 //TODO: Compute speedup
		 System.out.println("Parallel speedup: " + (serRunTime/parRunTime));

	}

}
