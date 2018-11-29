/**
 * 
 */
package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;

public class ResidualGraphImpl<V> implements ResidualGraph<V>, ExerciseSubmission {
	
	private Map<V, List<ResidualEdge<V>>> graph = new HashMap<V, List<ResidualEdge<V>>>();
	
	public ResidualGraphImpl(FlowGraph<V> flowGraph) {
		this.graph = new HashMap<V, List<ResidualEdge<V>>>();
		Set<V> originalNodes = flowGraph.getNodes();
		// iterate through the nodes from original graph
		for (V node : originalNodes) {
			List<ResidualEdge<V>> currentResidualEdges = new ArrayList<ResidualEdge<V>>();
			this.graph.put(node, currentResidualEdges);
		}
		// iterate through all edges from the graph
		Collection<FlowEdge<V>> currentEdges = flowGraph.getEdges();
		for (FlowEdge<V> edge : currentEdges) {
			ResidualEdgeImpl<V> residualEdge = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), 0);
			residualEdge.capacity = edge.getCapacity() - edge.getFlow();
			this.graph.get(residualEdge.start).add(residualEdge);
		}
	}
	
	// Gives all edges existing in the graph.
	@Override
	public List<ResidualEdge<V>> getEdges() {
		Collection<List<ResidualEdge<V>>> allValues = graph.values();
		List<ResidualEdge<V>> resultingEdges = new ArrayList<ResidualEdge<V>>();
		for (List<ResidualEdge<V>> value : allValues) {
			resultingEdges.addAll(value);
		}
		return resultingEdges;
	}
	
	// Gives all nodes existing in the graph.
	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}
	
	// Returns the edges leaving a particular node.
	@Override
	public List<ResidualEdge<V>> edgesFrom(V node) {
		if (graph.get(node) == null) {
			throw new NoSuchElementException ("Argument can not be null.");
		}
		List<ResidualEdge<V>> originalEdges = this.getEdges();
		List<ResidualEdge<V>> resultingEdges = new ArrayList<ResidualEdge<V>>();
		for (ResidualEdge<V> currentEdge : originalEdges) {
			if (currentEdge.getStart() == node)
				resultingEdges.add(currentEdge);
		}
		return resultingEdges;
	}
	
	// Returns the edge between node start and node end. Return null if no edge exists.
	@Override
	public ResidualEdge<V> getEdge(V start, V end) {
		if (start == null || end == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		if (this.graph.get(start) == null || this.graph.get(end) == null) 
			throw new NoSuchElementException("Either start or end node is not present in the graph.");
		
		List<ResidualEdge<V>> allEdges = getEdges();
		for (ResidualEdge<V> currentEdge : allEdges) {
			if (currentEdge.getStart() == start && currentEdge.getEnd() == end)
				return currentEdge;
		}
		return null;
	}
	
	// Returns team identifier 
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
