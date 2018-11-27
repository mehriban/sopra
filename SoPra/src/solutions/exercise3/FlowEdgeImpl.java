/**
 * 
 */
package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * @author Mehriban Malik
 * @param <V>
 *
 */
public class FlowEdgeImpl<V> extends Edge<V> implements FlowEdge<V>, ExerciseSubmission {

	private int flow;
	
	public FlowEdgeImpl(V start, V end, int capacity) {
		this.flow = 0;
		super.start = start;
		super.end = end;
		super.capacity = capacity;
	}
	
	@Override
	public void setCapacity(int capacity) {
		super.capacity = capacity;
	}
	
	@Override
	public int getCapacity() {
		return super.capacity;
	}

	@Override
	public V getEnd() {
		return super.end;
	}

	@Override
	public V getStart() {
		return super.start;
	}

	@Override
	public int getFlow() {
		return this.flow;
	}

	@Override
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
