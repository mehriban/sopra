package solutions.exercise3;

import org.sopra.api.exercises.exercise3.ResidualEdge;

public class ResidualEdgeImpl<V> extends Edge<V> implements ResidualEdge<V> {

    private ResidualEdge<V> reverse;

    public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
	this.endNode = endNode;
	this.startNode = startNode;
	this.capacity = capacity;
    }

    @Override
    public void addFlow(int amount) {
	if (amount < 0) {
	    reverse.addFlow(-amount);
	    return;
	}

	if (amount > capacity) {
	    throw new IllegalArgumentException(
		    "Cannot push " + amount + " units of flow across edge of capacity " + capacity);
	}

	capacity -= amount;
	reverse.setCapacity(reverse.getCapacity() + amount);
    }

    @Override
    public ResidualEdge<V> getReverse() {
	return reverse;
    }

    @Override
    public void setReverse(ResidualEdge<V> reverse) {
	this.reverse = reverse;
    }

}
