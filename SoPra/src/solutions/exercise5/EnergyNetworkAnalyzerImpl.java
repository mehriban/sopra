package solutions.exercise5;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;
import org.sopra.api.exercises.exercise5.AbstractEnergyNetworkAnalyzer;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.producer.Producer;

import solutions.exercise3.FlowGraphImpl;
import solutions.exercise4.FordFulkersonImpl;

/**
 * This class is an implementation of an energy network analyzer which is used to calculate 
 * maximum loads for power lines, producers & consumers in an energy networks.
 * 
 * @author Mehriban Kurbanova
 *
 */
public class EnergyNetworkAnalyzerImpl extends AbstractEnergyNetworkAnalyzer implements ExerciseSubmission {
	
	/**
	 * Constructs the energy network analyzer by calling the super interface.
	 * 
	 * @param graph
	 * @param producerCapacities
	 * @param consumerCapacities
	 */
	public EnergyNetworkAnalyzerImpl(Graph<EnergyNode, PowerLine> graph,
			Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities) {
		super(graph, producerCapacities, consumerCapacities);
	}
	
	/**
	 * Calculates the maximum flow of AbstractEnergyNetworkAnalyzer.flowGraph and stores the results in 
	 * AbstractEnergyNetworkAnalyzer.producerLevels, AbstractEnergyNetworkAnalyzer.consumerLevels and 
	 * AbstractEnergyNetworkAnalyzer.powerlineLevels.
	 * 
	 */
	@Override
	public void calculateMaxFlow() {
		FordFulkerson<EnergyNode> fordFulkerson = new FordFulkersonImpl<EnergyNode>();
		
		// first find max flow
		fordFulkerson.findMaxFlow(flowGraph, super_source, super_sink);
		
		// then update levels
		// update node levels
		Set<EnergyNode> nodes = flowGraph.getNodes();
		for (EnergyNode node : nodes) {
			if (node instanceof Producer) {
				this.producerLevels.put((Producer) node, flowGraph.getEdge(super_source, node).getFlow());
			}
			else if (node instanceof Consumer) {
				this.consumerLevels.put((Consumer) node, flowGraph.getEdge(node, super_sink).getFlow());
			}
		}
		
		// update powerline levels
		for (Entry<PowerLine, Integer> powerline : this.getPowerLineLevels().entrySet()) {
			int newLevelForward = flowGraph.getEdge(powerline.getKey().getStart(), powerline.getKey().getEnd()).getFlow();
			int newLevelOpposite = flowGraph.getEdge(powerline.getKey().getEnd(), powerline.getKey().getStart()).getFlow();
			int newLevel = Math.max(newLevelForward, newLevelOpposite);
			
			this.powerlineLevels.put(powerline.getKey(), newLevel);
		}
	}
	
	/**
	 * Creates a flow graph on base of the given energy graph.
	 * 
	 */
	@Override
	public FlowGraph<EnergyNode> createFlowGraph(Graph<EnergyNode, PowerLine> graph,
			Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities) {
		FlowGraph<EnergyNode> resultGraph = new FlowGraphImpl<EnergyNode>();
		List<PowerLine> powerlines = graph.getEdges();
		Set<EnergyNode> nodes = graph.getNodes();
		
		// add nodes
		for (EnergyNode node : nodes) {
			resultGraph.addNode(node);
		}
		
		// add additionally super source and super sink
		resultGraph.addNode(super_sink);
		resultGraph.addNode(super_source);
		
		// add flow edges
		for (PowerLine powerline : powerlines) {
			resultGraph.addEdge(powerline.getStart(), powerline.getEnd(), powerline.getCapacity());
			resultGraph.addEdge(powerline.getEnd(), powerline.getStart(), powerline.getCapacity());
		}
		
		Set<EnergyNode> resultNodes = resultGraph.getNodes();
		
		// add additional edges from sources and sinks
		for (EnergyNode node : resultNodes) {
			
			// add edges from&to super_source 
			if (node instanceof Producer) {
				int capacity = ((Producer) node).getProvidedPower();
				if (producerCapacities.isPresent()) {
					capacity = producerCapacities.get().get(node);
				}
				resultGraph.addEdge(super_source, node, capacity);
				resultGraph.addEdge(node, super_source, capacity);
			}
			
			// add edges from&to super_sink
			else if (node instanceof Consumer) {
				int capacity = ((Consumer) node).getRequiredPower();
				if (consumerCapacities.isPresent()) {
					capacity = consumerCapacities.get().get(node);
				}
				resultGraph.addEdge(super_sink, node, capacity);
				resultGraph.addEdge(node, super_sink, capacity);
			}
		}
		
		return resultGraph;
	}
	
	/**
	 * Returns the team identifier
	 * 
	 * @return team identifier
	 */
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
	
}
