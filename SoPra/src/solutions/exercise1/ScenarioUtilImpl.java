package solutions.exercise1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.ScenarioUtil;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

/**
 * This class implements the abstract class ScenariUtil and
 * provides helper methods for scenarios
 * @author Mehriban Kurbanova
 *
 */
public class ScenarioUtilImpl implements ExerciseSubmission, ScenarioUtil {
	
	/**
	 * Returns playfield elements from the given scenario by the given typ
	 * @param scenario the given scenario
	 * @param typ the given type
	 */
	@Override
	public List<PlayfieldElement> getPlayfieldElementsByType(Scenario scenario, ElementType type) {
		if (scenario == null || type == null) {
		//check for null parameters
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
			//if at least 1 parameter is null, then throw the exception
		} 
		Deque<PlayfieldElement> ret = new ArrayDeque<>();
		// A Deque is a Double-Ended-Queue. A Queue is a special List, where you just can add something new at the beginning
		for (int y = 0; y < scenario.getPlayfield().getHorizontalSize(); y++) {
		//Iterate over the whole play field in horizontal direction (x-axes)
			for (int x = scenario.getPlayfield().getVerticalSize() - 1; x >= 0; x--) {
				//Iterate over the whole play field in vertical direction (y-axes)
				PlayfieldElement element = scenario.getPlayfield().getPlayfieldElement(x, y);
				//Take the play field element by the iterated position (x,y)
				if (element.getElementType() == type) {
				//check if the element type is the same as the searched one
					ret.add(element);
					//if it is the same, then add the current play field element to the result list
				}
			}
		}
		List<PlayfieldElement> result = new ArrayList<PlayfieldElement>(ret);
		return result;
		// give the result back
	}

	/**
	 * Returns controllable producers for the given graph
	 * @param graph the given graph
	 */
	@Override
	public List<ControllableProducer> getControllableProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}
		
		List<EnergyNode> nodes = new ArrayList<EnergyNode>(graph.getNodes());
		List<ControllableProducer> result = new ArrayList<ControllableProducer>();
		for (EnergyNode node : nodes) {
			if (node instanceof ControllableProducer)
				result.add((ControllableProducer) node);
		}
		return result;
	}
	
	/**
	 * Returns controllable consumers from the given graph
	 * @param graph the given graph
	 */
	@Override
	public List<ControllableConsumer> getControllableConsumers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}
		
		List<EnergyNode> nodes = new ArrayList<EnergyNode>(graph.getNodes());
		List<ControllableConsumer> result = new ArrayList<ControllableConsumer>();
		for (EnergyNode node : nodes) {
			if (node instanceof ControllableConsumer)
				result.add((ControllableConsumer) node);
		}
		return result;
	}
	
	/**
	 * Returns all producers from the given graph
	 * @param graph the given graph
	 */
	@Override
	public List<Producer> getProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}
		
		List<EnergyNode> nodes = new ArrayList<EnergyNode>(graph.getNodes());
		List<Producer> result = new ArrayList<Producer>();
		for (EnergyNode node : nodes) {
			if (node instanceof Producer)
				result.add((Producer) node);
		}
		return result;
	}
	
	/**
	 * Returns all consumers from the given graph
	 * @param the given graph
	 */
	@Override
	public List<Consumer> getConsumers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}
		
		List<EnergyNode> nodes = new ArrayList<EnergyNode>(graph.getNodes());
		List<Consumer> result = new ArrayList<Consumer>();
		for (EnergyNode node : nodes) {
			if (node instanceof Consumer)
				result.add((Consumer) node);
		}
		return result;
	}

	/**
	 * Returns team identifier
	 */
	@Override
	public String getTeamIdentifier() {
	    return "G06T02AB1";
	}
	
	/**
	 * Returns all power lines from the given graph by the given type
	 * @param graph the given graph
	 * @param type the given type
	 */
	@Override
	public List<PowerLine> getPowerLinesByType(Graph<EnergyNode, PowerLine> graph,
            PowerLineType type) {
		if (graph == null || type == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}
		List<PowerLine> edges = graph.getEdges();
		List<PowerLine> result = new ArrayList<PowerLine>();
		for (PowerLine edge : edges) {
			PowerLineType currentType = edge.getType();
			if (currentType.equals(type))
				result.add(edge);
		}
		return result;
	}
}
