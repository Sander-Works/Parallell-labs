package no.hiof.itf23019.graph_search.bfs.parallel;

import no.hiof.itf23019.common_structure.graph.*;
import no.hiof.itf23019.common_structure.tree.TreeNode;
import no.hiof.itf23019.graph_search.bfs.serial.SerialBreadthFirstSearch;
import no.hiof.itf23019.graph_search.dfs.serial.SerialDepthFirstSearch;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BreadthFirstSearchTask implements Runnable{

	//TODO: Implement parallel task BFS here
    //Hint: Refer to the DepthFirstSearchTask
    private ConcurrentLinkedQueue<TreeNode> nodes;


    public BreadthFirstSearchTask(ConcurrentLinkedQueue<TreeNode> nodes) {
        this.nodes = nodes;
    }
    @Override
    public void run() {
        while(!this.nodes.isEmpty())
        {
            TreeNode node = nodes.poll();

            if(node != null && !node.isVisited())
            {
                new SerialBreadthFirstSearch().serialBFS(node);
            }

        }

    }

}
