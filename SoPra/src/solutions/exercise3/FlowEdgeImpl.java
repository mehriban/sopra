package solutions.exercise3;

import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowEdgeImpl<V> extends Edge<V> implements FlowEdge<V> {

    private int flow;

    public FlowEdgeImpl(V startNode, V endNode, int capacity) {
	this.startNode = startNode;
	this.endNode = endNode;
	this.capacity = capacity;
	this.flow = 0;
    }

    @Override
    public int getFlow() {
	return flow;
    }

    @Override
    public void setFlow(int flow) {
	if (flow < 0) {
	    throw new IllegalArgumentException("Flow must be positive!");
	}

	this.flow = flow;
    }

}
