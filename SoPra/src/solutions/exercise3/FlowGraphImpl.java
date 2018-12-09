package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
	graph = new HashMap<V, Map<V, FlowEdge<V>>>();
    }

    @Override
    public boolean addNode(V node) {
	if (node == null || graph.containsKey(node)) {
	    return false;
	}

	graph.put(node, new HashMap<V, FlowEdge<V>>());
	return true;
    }

    @Override
    public FlowEdge<V> addEdge(V start, V dest, int capacity) {
	if (!graph.containsKey(start) || !graph.containsKey(dest))
	    throw new NoSuchElementException("Both nodes must exist in the graph.");

	if (!graph.get(start).containsKey(dest))
	    graph.get(start).put(dest, new FlowEdgeImpl<V>(start, dest, capacity));

	return graph.get(start).get(dest);
    }

    @Override
    public List<FlowEdge<V>> getEdges() {
	Iterator<Map<V, FlowEdge<V>>> itMaps = graph.values().iterator();
	List<FlowEdge<V>> edges = new ArrayList<FlowEdge<V>>();

	while (itMaps.hasNext())
	    edges.addAll(itMaps.next().values());

	return edges;
    }

    @Override
    public Set<V> getNodes() {
	return graph.keySet();
    }

    @Override
    public boolean containsNode(V node) {
	return graph.containsKey(node);
    }

    @Override
    public Collection<FlowEdge<V>> edgesFrom(V node) {
	Map<V, FlowEdge<V>> arcs = graph.get(node);
	if (arcs == null) {
	    throw new NoSuchElementException("Source node does not exist");
	}

	return arcs.values();
    }

    @Override
    public FlowEdge<V> getEdge(V start, V end) {
	if (start == null || end == null) {
	    return null;
	}
	return (graph.get(start) != null) ? graph.get(start).get(end) : null;
    }

    @Override
    public String getTeamIdentifier() {
	return "Musterloesung";
    }

}
