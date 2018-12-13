package solutions.exercise4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualEdgeImpl;
import solutions.exercise3.ResidualGraphImpl;

/**
 * @author Mehriban Kurbanova
 *
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {
	
	public FordFulkersonImpl() {
		
	}

	/**
	 * Finds the minimum capacity along the given path and adds the minimum capacity to flow of each edges of the path.
	 * 
	 * @param path Augmented path (represented by a Deque) where minimum capacity of path is added to flow
	 * @throws java.lang.IllegalArgumentException - if parameter is null
	 */
	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path) {
		if (path == null)
			throw new IllegalArgumentException("Parameter path cannot be null");
		
		ArrayList<ResidualEdge<V>> pathAsArray = new ArrayList<ResidualEdge<V>>(path);
		
		// first save capacities of all edges of the path
		ArrayList<Integer> capacities = new ArrayList<Integer>();
		for (ResidualEdge<V> edge: pathAsArray) {
			capacities.add(edge.getCapacity());
		}
		
		// then find the minimum and add it to the flows of all edges of the path
		int minCapacity = Collections.min(capacities);
		for (ResidualEdge<V> edge : path) {
			edge.addFlow(minCapacity);
		}
	}

	/**
	 * Computes the maximum flow in a flow network from start to target.
	 * Given a flow network and a pair of nodes start & target, the algorithm calculates a maximum flow from start to target in given network.
	 * 
	 * @param graph Flow graph representing the flow network. Flows along edges in this graph will be set to max flows
	 * @param start Source node
	 * @param end Sink node
	 * @throws java.lang.IllegalArgumentException - If any parameter is null
	 * @throws java.util.NoSuchElementException - If start or target are not nodes in the graph
	 */
	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V end) {
		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Argument should not be null");
		}
		Set<V> nodes = graph.getNodes();
		if (!nodes.contains(start) || !nodes.contains(end)) {
			throw new NoSuchElementException("One of the give node is not in the graph.");
		}
		
		// this is an implementation of the formal FF-algorithm
		// found at http://www.inf.ufpr.br/pos/techreport/RT_DINF003_2004.pdf
		ResidualGraph<V> resGraph = new ResidualGraphImpl<V>(graph);
		
		Deque<ResidualEdge<V>> shortestPath = findPath(start, end, resGraph);
		
		while (shortestPath != null) {
			augmentPath(shortestPath);
		}
	}

	/**
	 * Finds the shortest path in the residual graph from and to the given nodes.
	 * The path is computed using the breadth-first search.
	 * 
	 * @param start the start node of the path
	 * @param end the end node of the path
	 * @param graph the residual graph
	 * @return a deque containing the shortest path in the reversed order.
	 * @throws java.lang.IllegalArgumentException - If at least one of the parameters is null
	 */
	@Override
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Parameter(s) can not be null!");
		}
		
		Map<V, Boolean> visited = new HashMap<V, Boolean>(); // saves already visited nodes
		Map<V, V> edgesFromStart = new HashMap<V, V>(); // contains edges for the current while step
	    Queue<V> queue = new LinkedList<V>();
	    
	    V current = start;
	    queue.add(current);
	    visited.put(current, true); // we mark the start node as visited
	    while(!queue.isEmpty()) {
	        current = queue.remove();
	        
	        // if the end node is found, the shortest path is already found
	        if (current.equals(end)) {
	            break;
	        }
	        else {
	        	// first retrieve all children for the current node, edges to children
	        	// should have capacity > 0
	        	List<ResidualEdge<V>> currentEdges = graph.edgesFrom(current);
	        	List<V> childrenNodes = new ArrayList<V>();
            	for (ResidualEdge<V> edge : currentEdges) {
            		if (edge.getCapacity() > 0 && current != edge.getEnd()) {
            			childrenNodes.add(edge.getEnd());
            		}
            	}
	        	
            	// once children are found, add them to the queue, mark as visited
            	// and keep track of the edges in the reversed order
	            for(V node :childrenNodes){
	            	// an already visited child will not be considered anymore
	                if(!visited.containsKey(node)){
	                    queue.add(node);
	                    visited.put(node, true);
	                    edgesFromStart.put(node, current);
	                }
	            }
	        }
	    }
	    
	    // if last node was not the end node, then the path was not found
	    if (!current.equals(end)){
	        return null;
	    }
	    
	    // extract the path to the list in the reversed order
	    List<V> pathAsList = new LinkedList<V>();
	    for(V node = end; node != null; node = edgesFromStart.get(node)) {
	        pathAsList.add(node);
	    }
	    
	    // convert the list to deque with residual edges
	    Deque<ResidualEdge<V>> result = new LinkedList<ResidualEdge<V>>();
	    ResidualEdge<V> edge;
	    for (int i = 0; i <= pathAsList.size() - 2; i++) {
	    	edge = new ResidualEdgeImpl<V>(pathAsList.get(i+1), pathAsList.get(i), graph.getEdge(pathAsList.get(i+1), pathAsList.get(i)).getCapacity());
	    	result.add(edge);
	    }
	    
	    return result;
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
}
