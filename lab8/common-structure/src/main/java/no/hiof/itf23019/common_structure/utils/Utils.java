package no.hiof.itf23019.common_structure.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import no.hiof.itf23019.common_structure.graph.Graph;
import no.hiof.itf23019.common_structure.graph.GraphEdge;
import no.hiof.itf23019.common_structure.graph.GraphNode;
import no.hiof.itf23019.common_structure.tree.Tree;
import no.hiof.itf23019.common_structure.tree.TreeNode;

public class Utils {

	public static void printGraph(Graph g)
	{
		for (GraphNode node : g.getNodes()) {
	        List<GraphEdge> edges = node.getEdges();

	        if (edges.isEmpty()) {
	            System.out.println("Node " + node.getName() + " has no edges.");
	            continue;
	        }
	        System.out.print("Node " + node.getName() + " has edges to: ");

	        for (GraphEdge edge : edges) {
	            System.out.print(edge.getDestination().getName() + "(" + edge.getWeight() + ") ");
	        }
	        System.out.println();
	    }
	}
	
	public static Graph generateGraph(int n, boolean directed)
	{
		Graph g = new Graph(directed);
	
		Random rand1 = new Random(2021220201);
		Random rand2 = new Random(2021220202);

		TreeMap<Integer, GraphNode> nodes = new TreeMap<>();
		
		for(int i = 0; i < n; i++)
		{
			GraphNode s = new GraphNode(i, String.valueOf(i));
			nodes.put(i, s);
			g.addNode(s);
		}
		for(int i = 0; i < n; i++)
		{
			GraphNode s = nodes.get(i);
			
			for(int j = i + 1; j < n; j++)
			{
				GraphNode d = nodes.get(j);
				
				if(rand1.nextInt(100) <= 90)
					g.addEdge(s, d, (rand2.nextInt(n*10) + 1));
			}
		}
		
		//g.addNodes(new ArrayList<>(nodes.values()));
		return g;
	}
	
	
	public static Tree generateTree(int maxChild, int numNodes)
	{
		int i = 0;
		TreeNode root = new TreeNode(i, String.valueOf(i));
		Tree tree = new Tree(root);
		
		Random rand = new Random(20212202);
		

		List<TreeNode> parents = new LinkedList<TreeNode>();
		List<TreeNode> children = new LinkedList<TreeNode>();
		
		parents.add(root);
		
	
		
		while(i < numNodes)
		{
			for(TreeNode n : parents)
			{
				int numChild = rand.nextInt(maxChild) + 1;
				for(int j = 1; j <= numChild; j++)
				{
					TreeNode childNode = new TreeNode(++i, String.valueOf(i));
					n.addChild(childNode);
					children.add(childNode);
				}
			}
			
			parents.clear();
			
			List<TreeNode> temp = parents;
			parents = children;
			children = temp;
				
		}
		
		System.out.println("Size of Tree is: " + i);
		
		return tree;
	}
}
