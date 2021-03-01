package no.hiof.itf23019.common_structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Graph {
	// Each node maps to a list of all his neighbors
	private Set<GraphNode> nodes;
	private boolean directed;

	public Graph(boolean directed) {
	    this.directed = directed;
	    nodes = new ConcurrentSkipListSet<GraphNode>();
	}
	
	// Doesn't need to be called for any node that has an edge to another node
	// since addEdge makes GraphNode that both nodes are in the nodes Set
	public void addNode(GraphNode... n) {
	    // We're using a var arg method so we don't have to call
	    // addNode repeatedly
	    nodes.addAll(Arrays.asList(n));
	}
	
	public void addNodes(List<GraphNode> nodes)
	{
		this.nodes.addAll(nodes);
	}
	

	public void addEdge(GraphNode source, GraphNode destination, double weight) {
	    source.addNeigbour(destination, weight);
	    

	    if (!directed) 
	       destination.addNeigbour(source, weight);
	}
	
	public boolean hasEdge(GraphNode source, GraphNode destination) {
	   
	    return source.isNeighbour(destination);
	}
	

	public List<GraphNode> getNodes()
	{
		return new ArrayList<>(nodes);
	}
	
	public int size()
	{
		return nodes.size();
	}
	

	
	
}
