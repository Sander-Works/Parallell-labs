package no.hiof.itf23019.common_structure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphNode implements Comparable<GraphNode>{
	   	int id;
	    String name;
	   
	    private Map<GraphNode, GraphEdge> edges;
	    

	    public GraphNode(int id, String name) {
	        this.id = id;
	        this.name = name;
	      
	        edges = new ConcurrentHashMap<GraphNode, GraphEdge>();
	    }
	    
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    
	    public void addNeigbour(GraphNode neigbour, double weight)
	    {
	    	GraphEdge edge = null;
		    if(edges.containsKey(neigbour))
		    {
		    	edge = edges.get(neigbour);
		    	edge.setWeight(weight);
		    }
		    else
		    	edge =  new GraphEdge(this, neigbour, weight);
		    
		    edges.put(neigbour, edge);
	    }
	    
	    public List<GraphEdge> getEdges() {
			return new ArrayList<GraphEdge>(edges.values());
		}
	    
	    public GraphEdge getEdge(GraphNode neighbour)
	    {
	    	return edges.get(neighbour);
	    }
	    
	    public boolean isNeighbour(GraphNode neigbour)
	    {
	    	return edges.containsKey(neigbour);
	    	
	    }

		@Override
		public int compareTo(GraphNode o) {
			return this.id - o.id;
		}

		@Override
		public String toString() {
			return "Node[" + name + "]";
		}
	    
		
	    


}
