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

	/**
	 * 
	 */
	protected V start;
	protected V end;
	protected int capacity ;
	
	public int getCapacity()
	{
		return this.capacity;
	}
	
	public V getStart() 
	{
		return this.start;
	}
	
	public V getEnd() 
	{
		return this.end;
	}

	public void setCapacity(int cap) {
		this.capacity = cap;
		
	}

	
	
	
}
