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

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

	private Map<V, Map<V, FlowEdge<V>>> graph;

	public FlowGraphImpl() {
		this.graph = new HashMap<V, Map<V, FlowEdge<V>>>();
	}
	
	// Adds new node to graph if node does not already exist. If node is added returns true, else returns false.
	@Override
	public boolean addNode(V node) {
		if (node == null) {
			return false;
		}
		// we add the node to the graph if the graph does not contain it yet
		if (graph.get(node) == null) {
			graph.put(node, new HashMap<V, FlowEdge<V>>());
			return true;
		}
		else 
			return false;
	}
	
	// Adds a new FlowEdge to a start and a destination node with initial flow of 0.
	// If either start or destination node does not exist in graph, throws an NoSuchElementException. 
	// Returns the existing edge if the edge already exists.
	@Override
	public FlowEdge<V> addEdge(V start, V end, int capacity) {
		Map<V, FlowEdge<V>> startEdges = graph.get(start);
		if (startEdges == null || graph.get(end) == null) {
			throw new NoSuchElementException("Start node does not exist in the graph.");
		}
		FlowEdgeImpl<V> newEdge = new FlowEdgeImpl<V>(start, end, capacity);
		startEdges.put(end, newEdge);
		graph.put(start, startEdges);
		return newEdge;
	}
	
	// Returns true if the given node is contained in the graph. Otherwise returns false
	@Override
	public boolean containsNode(V node) {
		if (node == null) {
			throw new IllegalArgumentException("Argument can not be null.");
		}
		if (graph.get(node) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Returns all edges from given node. If the node is not given in the graph, throws a NoSuchElementException
	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node) {
		if (node == null || graph.get(node) == null) {
			throw new IllegalArgumentException("Argument can not be null.");
		}
		List<FlowEdge<V>> originalEdges = this.getEdges();
		List<FlowEdge<V>> resultingEdges = new ArrayList<FlowEdge<V>>();
		for (FlowEdge<V> currentEdge : originalEdges) {
			if (currentEdge.getStart() == node)
				resultingEdges.add(currentEdge);
		}
		return resultingEdges;
	}
	
	// Returns a FlowEdge going from start to end. 
	// Returns null if FlowEdge is not present or at least one parameter is null.
	@Override
	public FlowEdge<V> getEdge(V start, V end) {
		if (start == null || end == null)
			return null;
		List<FlowEdge<V>> allEdges = getEdges();
		for (FlowEdge<V> currentEdge : allEdges) {
			if (currentEdge.getStart() == start && currentEdge.getEnd() == end)
				return currentEdge;
		}
		return null;
	}
	
	// Returns list of all edges from graph.
	@Override
	public List<FlowEdge<V>> getEdges() {
		Collection<Map<V, FlowEdge<V>>> allValues = graph.values();
		List<FlowEdge<V>> resultingEdges = new ArrayList<FlowEdge<V>>();
		for (Map<V, FlowEdge<V>> value : allValues) {
			resultingEdges.addAll(value.values());
		}
		return resultingEdges;
	}
	
	// Returns a set of all nodes from graph.
	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}