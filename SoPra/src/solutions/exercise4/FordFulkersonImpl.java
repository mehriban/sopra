package solutions.exercise4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

public class FordFulkersonImpl<V> implements FordFulkerson<V> {

	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
		// TODO Auto-generated method stub

	}

	@Override
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Parameter(s) can not be null!");
		}
		//Vergleich seiten 10-15 in "Folien04.pdf"
		Deque<ResidualEdge<V>> path = new LinkedList<ResidualEdge<V>>(); //Erreichbarkeitsbaum
		Deque<V> queue = new LinkedList<V>(); //Warteschlange
		
		queue.add(start); //Startknoten zur Warteschlange hinzufügen
		queue.poll(); //ersten knoten der Warteschlange entfernen unt returnen
		path.addAll(graph.edgesFrom(start)); //alle kanten ausgehend vom erste knoten dem erreichbarkeitsbaum hinzufügen
		
		
		
		
		return null;
	}
}