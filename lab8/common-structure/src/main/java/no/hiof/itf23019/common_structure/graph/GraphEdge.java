package no.hiof.itf23019.common_structure.graph;

public class GraphEdge implements Comparable<GraphEdge>{
	private GraphNode source, destination;
	private double weight;
	
	public GraphEdge(GraphNode s, GraphNode d, double w) {
		source = s;
		destination = d;
		weight = w;
	}

	public GraphNode getSource() {
		return source;
	}

	public void setSource(GraphNode source) {
		this.source = source;
	}

	public GraphNode getDestination() {
		return destination;
	}

	public void setDestination(GraphNode destination) {
		this.destination = destination;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(GraphEdge o) {
		 if (this.weight > o.weight) 
		        return 1;
		    
		 else if(this.weight == o.weight)
			 return 0;
		    
		 else return -1;
	}
	
    public String toString() {
        return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
    }
}
