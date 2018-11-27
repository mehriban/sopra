package solutions.exercise3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.model.Edge;

public class FlowGraphImpl<V> implements FlowGraph<V> {

	private Map<V, Map<V, FlowEdge<V>>> outerMap;
	private FlowEdgeImpl<V> edge;
	private Collection<FlowEdge<V>> resultingEdges;

	public FlowGraphImpl() {
		this.outerMap = new HashMap<V, Map<V, FlowEdge<V>>>();
	}

	@Override
	public FlowEdge<V> addEdge(V start, V end, int capacity) {
		if (this.getEdge(start, end) == null) {
			if (this.containsNode(start) && this.containsNode(end)) {
				this.edge = new FlowEdgeImpl<V>(start, end, capacity);
			} else {
				throw new NoSuchElementException("Start node or destination node does not exist in graph");
			}
			return null;
		} else {
			return this.getEdge(start, end);
		}
	}

	@Override
	public boolean addNode(V node) {
		if (this.containsNode(node)) {
			return false;
		} else {
			// TODO
			return true;
		}
	}

	@Override
	public boolean containsNode(V node) {
		if (outerMap.containsValue(node) || outerMap.containsKey(node)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node) {
		List<FlowEdge<V>> edges = this.getEdges();
		for (int i=0; i < edges.size(); i++) {
			if (edge.getEnd() == node || edge.getStart() == node) {
				resultingEdges.add(edge);
			}
		}
		return resultingEdges;
	}

	@Override
	public FlowEdge<V> getEdge(V start, V end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlowEdge<V>> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

}
