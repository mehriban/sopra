package solutions.exercise3;

import org.sopra.api.exercises.exercise3.ResidualEdge;

public class ResidualEdgeImpl<V> extends Edge<V> implements ResidualEdge<V> {
	private ResidualEdge<V> reverse;
	public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
		super.start = startNode;
		super.end = endNode;
		super.capacity = capacity;
	}

	@Override
	public String getTeamIdentifier() {
		return "G02T06";
	}
	
	// Adds or subtracts the indicated number of flow units across this edge. 
	// Adding or removing a unit of flow from an edge works by decreasing or increasing the capacity of this edge as appropriate, then increasing or decreasing the capacity of its residual edge appropriately.
	@Override
	public void addFlow(int flow) {
		if (flow > super.capacity || flow > this.reverse.getCapacity())
			throw new IllegalArgumentException("New amount of flow exceeded the capacity of edge.");
		
		super.capacity -= flow;
		this.reverse.setCapacity(this.reverse.getCapacity() + flow);	
	}
	
	// Returns the reverse edge
	@Override
	public ResidualEdge<V> getReverse() {
		return this.reverse;
	}
	
	// Sets the reverse edge associated with this edge.
	@Override
	public void setReverse(ResidualEdge<V> reverse) {
		this.reverse = reverse;		
	}
	
	// Gets the capacity of this edge
	@Override
	public int getCapacity() {
		return super.capacity;
	}
	
	// Gets the start node of this edge.
	@Override
	public V getStart() {
		return super.start;
	}
	
	// Gets the end node of this edge.
	@Override
	public V getEnd() {
		return super.end;
	}
	
	// Sets the capacity of this edge.
	@Override
	public void setCapacity(int cap) {
		if (cap < 0) 
			throw new IllegalArgumentException("Parameter cannot be negative.");
		super.capacity = cap;
	}

}
