package no.hiof.itf23019.graph_search;

import no.hiof.itf23019.common_structure.tree.Tree;
import no.hiof.itf23019.common_structure.utils.Utils;
import no.hiof.itf23019.graph_search.dfs.parallel.ParallelDepthFirstSearch;
import no.hiof.itf23019.graph_search.dfs.serial.SerialDepthFirstSearch;

public class DfsMain {

	public static void main(String[] args) {
		 long startTime, endTime, serRunTime=0, parRunTime=0;
		 SerialDepthFirstSearch serialDepthFirstSearch = new SerialDepthFirstSearch();
		 ParallelDepthFirstSearch parallelDepthFirstSearch = new ParallelDepthFirstSearch();
		 
		 System.out.println("Generating tree...");
		 Tree tree = Utils.generateTree(10, 10_000_000);
		 
		 for(int run = 1; run <= 10; run++)
		 {
			 System.out.println("RUN #" + run);
			 
			 System.out.println("Running SerialDFS...");
			 startTime = System.currentTimeMillis();
			 serialDepthFirstSearch.serialDFS(tree.getRoot());
			 endTime = System.currentTimeMillis();
			 serRunTime += endTime - startTime;
			 System.out.println("Serial version took " + (endTime - startTime) + " miliseconds");
			 
			 tree.unvisit();
			 
			 System.out.println("Running ParallelDFS...");
			 startTime = System.currentTimeMillis();
			 parallelDepthFirstSearch.parallelDFS(tree.getRoot());
			 endTime = System.currentTimeMillis();
			 parRunTime += endTime - startTime;
			 System.out.println("Parallel version took " + (endTime - startTime) + " miliseconds");
			 
			 tree.unvisit();
		 }
		
		 //TODO: Compute speed up
		 
	}

}
