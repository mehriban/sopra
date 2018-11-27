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
import org.sopra.api.model.Edge;

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

	private Map<V, Map<V, FlowEdge<V>>> graph;

	public FlowGraphImpl() {
		this.graph = new HashMap<V, Map<V, FlowEdge<V>>>();
	}
	
	@Override
	public boolean addNode(V node) {
		if (node == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		// we add the node to the graph if the graph does not contain it yet
		if (!containsNode(node)) {
			graph.put(node, new HashMap<V, FlowEdge<V>>());
			return true;
		}
		else 
			return false;
	}

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

	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node) {
		if (node == null) {
			throw new IllegalArgumentException("Argument can not be null.");
		}
		List<FlowEdge<V>> originalEdges = this.getEdges();
		List<FlowEdge<V>> resultingEdges = this.getEdges();
		for (FlowEdge<V> currentEdge : originalEdges) {
			if (currentEdge.getStart() == node || currentEdge.getEnd() == node)
				resultingEdges.add(currentEdge);
		}
		return resultingEdges;
	}

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

	@Override
	public List<FlowEdge<V>> getEdges() {
		Collection<Map<V, FlowEdge<V>>> allValues = graph.values();
		List<FlowEdge<V>> resultingEdges = new ArrayList<FlowEdge<V>>();
		for (Map<V, FlowEdge<V>> value : allValues) {
			resultingEdges.addAll(value.values());
		}
		return resultingEdges;
	}

	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}