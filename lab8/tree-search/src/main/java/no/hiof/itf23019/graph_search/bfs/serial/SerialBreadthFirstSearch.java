package no.hiof.itf23019.graph_search.bfs.serial;

import java.util.LinkedList;
import java.util.Queue;

import no.hiof.itf23019.common_structure.tree.TreeNode;

public class SerialBreadthFirstSearch {

	public void serialBFS(TreeNode root) {
		Queue<TreeNode> nodes = new LinkedList<>();
		nodes.add(root);

		while (!nodes.isEmpty()) {
			TreeNode node = nodes.poll();
			if (!node.isVisited()) {
				
				// System.out.print(node.getName() + " ");
				
				//TODO: continue the implementation of BFS
				// Visit the node
				// Add the child to the Queue
				
			}
		}
	}

}
