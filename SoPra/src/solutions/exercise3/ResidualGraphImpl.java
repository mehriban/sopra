package solutions.exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;

public class ResidualGraphImpl<V> implements ExerciseSubmission, ResidualGraph<V> {

	private Map<V, List<ResidualEdge<V>>> graph = new HashMap<V, List<ResidualEdge<V>>>();

	public ResidualGraphImpl(FlowGraph<V> flowGraph) {

		List<FlowEdge<V>> visited = new ArrayList<FlowEdge<V>>();

		for (V node : flowGraph.getNodes()) {
			graph.put(node, new ArrayList<ResidualEdge<V>>());
		}

		for (V start : flowGraph.getNodes()) {
			for (FlowEdge<V> edge : flowGraph.edgesFrom(start)) {
				if (!visited.contains(edge)) {
					ResidualEdge<V> forward;
					ResidualEdge<V> reverse;
					int c_1 = edge.getCapacity();
					int f_1 = edge.getFlow();

					FlowEdge<V> rev_edge = flowGraph.getEdge(edge.getEnd(), start);

					if (rev_edge != null) {
					    int c_2 = rev_edge.getCapacity();
					    int f_2 = rev_edge.getFlow();
					    if(f_1>0 && f_2==0) {
						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1);
						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), c_2 + f_1);
					    } else if (f_2>0 && f_1==0){
						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 + f_2);
						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), c_2 - f_2);
					    } else if (f_1==0 && f_2==0) {
						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1);
						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), c_2);
					    } else {
						throw new IllegalArgumentException("Flow in both directions not possible. Look up edges from/to: "+edge.getStart()+" <-> "+edge.getEnd());
					    }
					    visited.add(rev_edge);
					} else {
					    forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1);
					    reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), f_1);
					}

					forward.setReverse(reverse);
					reverse.setReverse(forward);

					graph.get(edge.getStart()).add(forward);
					graph.get(edge.getEnd()).add(reverse);
				}
			}

		}

	}

	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}

	@Override
	public List<ResidualEdge<V>> getEdges() {

		Iterator<List<ResidualEdge<V>>> itEdges = graph.values().iterator();
		List<ResidualEdge<V>> edges = new ArrayList<ResidualEdge<V>>();

		while (itEdges.hasNext())
			edges.addAll(itEdges.next());

		return edges;
	}

	@Override
	public List<ResidualEdge<V>> edgesFrom(V node) {
		List<ResidualEdge<V>> edges = graph.get(node);
		if (edges == null)
			throw new NoSuchElementException("Node does not exist.");

		return edges;
	}

	@Override
	public ResidualEdge<V> getEdge(V start, V end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Invalid parameters.");
		}
		if (!graph.containsKey(start) || !graph.containsKey(end)) {
			throw new NoSuchElementException("Node not contained.");
		}
		for (ResidualEdge<V> edge : graph.get(start)) {
			if (edge.getEnd().equals(end)) {
				return edge;
			}
		}
		return null;
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
