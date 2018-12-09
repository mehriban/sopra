package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.CapacityEdge;

abstract class Edge<V> implements CapacityEdge<V>, ExerciseSubmission{

    protected int capacity;
    protected V startNode;
    protected V endNode;
	
    @Override
    public V getStart() {
	return startNode;
    }

    @Override
    public V getEnd() {
	return endNode;
    }

    @Override
    public int getCapacity() {
	return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
	if (capacity < 0) {
		throw new IllegalArgumentException("Capacity not allowed to be negative.");
	}
	this.capacity = capacity;
    }

    @Override
    public String getTeamIdentifier() {
	return "Musterloesung";
    }
    
}
