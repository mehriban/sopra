package solutions.exercise4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

/**
 * @author Mehriban Malik
 *
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {
	
	public FordFulkersonImpl() {
		
	}

	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path) {
		if (path == null)
			throw new IllegalArgumentException("Parameter path cannot be null");
		
		ArrayList<ResidualEdge<V>> pathAsArray = new ArrayList<ResidualEdge<V>>(path);	
		ArrayList<Integer> capacities = new ArrayList<Integer>();
		for (ResidualEdge<V> edge: pathAsArray) {
			capacities.add(edge.getCapacity());
		}
		
		int minCapacity = Collections.min(capacities);
		
		for (ResidualEdge<V> edge : path) {
			edge.addFlow(minCapacity);
		}
	}

	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V end) {
		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("");
		}
		
	}

	@Override
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Parameter(s) can not be null!");
		}
		//Vergleich seiten 10-15 in "Folien04.pdf"
		Deque<ResidualEdge<V>> path = new LinkedList<ResidualEdge<V>>(); //Erreichbarkeitsbaum
		Deque<V> queue = new LinkedList<V>(); //Warteschlange
		
		queue.add(start); //Startknoten zur Warteschlange hinzufuegen
		queue.poll(); //ersten knoten der Warteschlange entfernen unt returnen
		path.addAll(graph.edgesFrom(start)); //alle kanten ausgehend vom erste knoten dem erreichbarkeitsbaum hinzufuegen
		
		return path;
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
