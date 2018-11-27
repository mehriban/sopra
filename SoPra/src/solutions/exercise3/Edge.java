/**
 * 
 */
package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.CapacityEdge;

/**
 * @author Mehriban Malik
 *
 */
abstract class Edge<V> implements ExerciseSubmission, CapacityEdge<V>{
	protected V start;
	protected V end;
	protected int capacity;
	
	public abstract int getCapacity();
	
	public abstract V getStart();
	
	public abstract V getEnd();

	public abstract void setCapacity(int cap);
	
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
	
	
}
