package no.hiof.itf23019.dijkstra;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import no.hiof.itf23019.common_structure.graph.Graph;
import no.hiof.itf23019.common_structure.graph.GraphEdge;
import no.hiof.itf23019.common_structure.graph.GraphNode;
import no.hiof.itf23019.common_structure.utils.Utils;

public class DijkstraSingleSource {
	
	public LinkedHashMap<GraphNode, Double> serialDijkstra(Graph g, GraphNode s)
	{
		LinkedHashSet<GraphNode> visitedNodes = new LinkedHashSet<>();
		LinkedHashMap<GraphNode, Double> weights = new LinkedHashMap<>();
		int size = g.size();
		
		visitedNodes.add(s);
		
		for(GraphNode node : g.getNodes())
		{
			if(!node.equals(s))
			{
				GraphEdge edge =  s.getEdge(node);
				if(edge != null)
					weights.put(node, edge.getWeight());
				else
					weights.put(node, Double.POSITIVE_INFINITY);
			}
		}
		
		while(visitedNodes.size() < size)
		{
			GraphNode u = weights.entrySet().stream().filter(v -> !visitedNodes.contains(v.getKey())).min(Comparator.comparing(Entry::getValue)).get().getKey();
			visitedNodes.add(u);
			double weight_u = weights.get(u);
			
			if(weight_u != Double.POSITIVE_INFINITY)
				u.getEdges().stream().filter(v -> !visitedNodes.contains(v.getDestination()))
							.forEach(e -> {
								double weight_e = weights.get(e.getDestination());
								double new_weight_e =  weight_u + e.getWeight();
								if(new_weight_e < weight_e)
									weights.put(e.getDestination(), new_weight_e);
							});
		}
		
		return weights;
	}
	
	
	public LinkedHashMap<GraphNode, Double> parallelDijkstra(Graph g, GraphNode s)
	{
		
		//TODO: Implmenent parallel version with parallel stream
		//Hint: replace stream with parallelStream.
		LinkedHashSet<GraphNode> visitedNodes = new LinkedHashSet<>();
		LinkedHashMap<GraphNode, Double> weights = new LinkedHashMap<>();
		int size = g.size();

		visitedNodes.add(s);

		for(GraphNode node : g.getNodes())
		{
			if(!node.equals(s))
			{
				GraphEdge edge =  s.getEdge(node);
				if(edge != null)
					weights.put(node, edge.getWeight());
				else
					weights.put(node, Double.POSITIVE_INFINITY);
			}
		}

		while(visitedNodes.size() < size)
		{
			GraphNode u = weights.entrySet().parallelStream().filter(v -> !visitedNodes.contains(v.getKey())).min(Comparator.comparing(Entry::getValue)).get().getKey();
			visitedNodes.add(u);
			double weight_u = weights.get(u);

			if(weight_u != Double.POSITIVE_INFINITY)
				u.getEdges().stream().filter(v -> !visitedNodes.contains(v.getDestination()))
						.forEach(e -> {
							double weight_e = weights.get(e.getDestination());
							double new_weight_e =  weight_u + e.getWeight();
							if(new_weight_e < weight_e)
								weights.put(e.getDestination(), new_weight_e);
						});
		}

		return weights;
	}
	
	 public static void main(String[] args) {
		 
		 //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "40");
		 long startTime, endTime, serRunTime=0, parRunTime=0;
		 DijkstraSingleSource dijkstra = new DijkstraSingleSource();
		 System.out.println("Generating graph...");
		 Graph g = Utils.generateGraph(7_000, false);
		
		 for(int run = 1; run <= 10; run++)
		 {
			 System.out.println("RUN #" + run);
			 System.out.println("Running SerialDijkstra...");
			 startTime = System.currentTimeMillis();
			 LinkedHashMap<GraphNode, Double> weights = dijkstra.serialDijkstra(g, g.getNodes().get(0));
			 endTime = System.currentTimeMillis();
			 serRunTime += endTime - startTime;
			 System.out.println("Serial version took " + (endTime - startTime) + " miliseconds");

			 System.out.println("Running ParallelDijkstra...");
			 startTime = System.currentTimeMillis();
			 weights = dijkstra.parallelDijkstra(g, g.getNodes().get(0));
			 endTime = System.currentTimeMillis();
			 parRunTime += endTime - startTime;
			 System.out.println("parallel version took " + (endTime - startTime) + " miliseconds");
		 }
		 
		 //TODO: Compute the speedup

		 System.out.println("Parallell speedup: " + (serRunTime/parRunTime));
		 
	 }
	
	
	
}
