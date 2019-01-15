package solutions.exercise4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {

    @Override
    public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
	if (graph == null || start == null || target == null) {
	    throw new IllegalArgumentException("Parameters are not allowed to be null");
	}

	if (!graph.containsNode(start) || !graph.containsNode(target)) {
	    throw new NoSuchElementException("Start and end nodes must be in the flow network!");
	}

	if (start.equals(target)) {
	    return;
	}

	ResidualGraph<V> residualGraph = new ResidualGraphImpl<V>(graph);

	while (true) {
	    Deque<ResidualEdge<V>> path = findPath(start, target, residualGraph);

	    if (path == null) {
		break;
	    }

	    augmentPath(path);
	}

	for (V node : residualGraph.getNodes()) {
	    for (ResidualEdge<V> edge : residualGraph.edgesFrom(node)) {

		FlowEdge<V> flowEdge = graph.getEdge(edge.getEnd(), edge.getStart());
		FlowEdge<V> rev_flowEdge = graph.getEdge(edge.getStart(), edge.getEnd());

		if (flowEdge != null) {
		    flowEdge.setFlow(edge.getCapacity());
		}
		if (rev_flowEdge != null && flowEdge != null) {
		    if (edge.getCapacity() - flowEdge.getCapacity() >= 0) {
			flowEdge.setFlow(edge.getCapacity() - flowEdge.getCapacity());
		    } else {
			flowEdge.setFlow(0);
		    }
		}
	    }
	}
    }

    @Override
    public Deque<ResidualEdge<V>> findPath(V s, V t, ResidualGraph<V> graph) {
	if (s == null || t == null || graph == null) {
	    throw new IllegalArgumentException("Parameters are not allowed to be null.");
	}

	if (!graph.getNodes().contains(s) || !graph.getNodes().contains(t)) {
	    throw new NoSuchElementException("Start and end nodes must be in the flow network!");
	}

	HashSet<V> visitedNodes = new HashSet<V>();
	LinkedList<V> nodeQueue = new LinkedList<V>();

	HashMap<V, V> nodeMap = new HashMap<V, V>();
	boolean foundPathToTarget = false;

	visitedNodes.add(s);
	nodeQueue.add(s);

	while (!nodeQueue.isEmpty()) {
	    V currentNode = nodeQueue.poll();

	    for (ResidualEdge<V> edge : graph.edgesFrom(currentNode)) {
		V otherNode = edge.getEnd();

		if (!visitedNodes.contains(otherNode) && edge.getCapacity() > 0) {
		    visitedNodes.add(otherNode);
		    nodeMap.put(otherNode, currentNode);
		    nodeQueue.add(otherNode);
		    if (otherNode.equals(t)) {
			foundPathToTarget = true;
		    }
		}
	    }
	}

	if (foundPathToTarget) {

	    Deque<ResidualEdge<V>> result = new ArrayDeque<ResidualEdge<V>>();

	    V end = t;

	    while (nodeMap.containsKey(end)) {

		V start = nodeMap.get(end);

		final V tmpEnd = end;
		Optional<ResidualEdge<V>> retEdge = graph.edgesFrom(start).stream().filter(e -> e.getEnd().equals(tmpEnd)).findAny();

		if (!retEdge.isPresent()) {
		    throw new IllegalStateException("There should be an edge. Graph corrupted?");
		}

		result.add(retEdge.get());

		end = start;
	    }
	    return result;
	} else {
	    return null;
	}

    }

    @Override
    public void augmentPath(Deque<ResidualEdge<V>> path) {
	if (path == null) {
	    throw new IllegalArgumentException("Path is not allowed to be null.");
	}

	int capacity = Integer.MAX_VALUE;
	for (ResidualEdge<V> edge : path) {
	    capacity = Math.min(capacity, edge.getCapacity());
	}

	for (ResidualEdge<V> edge : path) {
	    edge.addFlow(capacity);
	}
    }

    @Override
    public String getTeamIdentifier() {
	return "Musterloesung";
    }

}
